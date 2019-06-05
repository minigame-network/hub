package me.chasertw123.minigames.hub.user;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;
import me.chasertw123.minigames.core.api.misc.Title;
import me.chasertw123.minigames.core.api.v2.CoreAPI;
import me.chasertw123.minigames.core.collectibles.Collectible;
import me.chasertw123.minigames.core.collectibles.CollectibleManager;
import me.chasertw123.minigames.core.collectibles.gadgets.GadgetCollectible;
import me.chasertw123.minigames.core.collectibles.morphs.MorphCollectible;
import me.chasertw123.minigames.core.collectibles.particles.ParticleCollectible;
import me.chasertw123.minigames.core.database.GenericDatabaseMethods;
import me.chasertw123.minigames.core.user.data.settings.Setting;
import me.chasertw123.minigames.core.utils.items.AbstractItem;
import me.chasertw123.minigames.core.utils.items.Items;
import me.chasertw123.minigames.hub.Main;
import me.chasertw123.minigames.hub.features.guis.collectibles.joinmessages.LoginMessage;
import me.chasertw123.minigames.hub.features.parkour.UserCourseCompletionData;
import me.chasertw123.minigames.hub.user.data.WaterWarsUserData;
import me.chasertw123.minigames.shared.database.Database;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import sun.net.www.content.text.Generic;

import javax.print.Doc;
import java.util.*;

/**
 * Created by Chase on 1/5/2018.
 */
public class User {

    private UUID uuid;
    private boolean loaded = false;
    private long lastVisibilityToggle = 0;

    private List<Collectible> unlockedCollectibles = new ArrayList<>();

    private LoginMessage selectedLoginMessage = LoginMessage.WALKED_IN; // TODO: Load and play on finish loading
    private ParticleCollectible activeParticle = null;
    private MorphCollectible activeMorph = null;
    private GadgetCollectible activeGadget = null;

    private WaterWarsUserData waterWarsData;
    private Map<String, Integer> parkourCourseData;

    @SuppressWarnings("unchecked")
    public User(UUID uuid) {
        this.uuid = uuid;

        this.parkourCourseData = new HashMap<>();

        Main.newChain()
                .asyncFirst(() ->  {

                    Database database = CoreAPI.getDatabase();
                    MongoCollection<Document> hubCollection = database.getMongoCollection(Database.Collection.HUB_USER),
                            waterWarsCollection = database.getMongoCollection(ServerGameType.WATER_WARS);

                    Map<String, Document> documents = new HashMap<>();
                    if (GenericDatabaseMethods.containsUser(hubCollection, uuid))
                        documents.put(Database.Collection.HUB_USER.getId(),
                                hubCollection.find(Filters.eq("uuid", uuid.toString())).first());

                    if (GenericDatabaseMethods.containsUser(waterWarsCollection, uuid))
                        documents.put(ServerGameType.WATER_WARS.toString(),
                                waterWarsCollection.find(Filters.eq("uuid", uuid.toString())).first());

                    return documents;
                })
                .syncLast((documents) -> {

                    CollectibleManager cm = me.chasertw123.minigames.core.Main.getCollectibleManager();
                    Document hubData = documents.getOrDefault(Database.Collection.HUB_USER.getId(), null);
                    if (hubData != null) {

                        this.unlockedCollectibles = cm.getUnlockedCollectibles((Document) hubData.get("unlocks"), getCoreUser());

                        this.unlockedCollectibles.stream()
                                .filter(c -> !hubData.getString("morph").equals("none") && cm.getById(hubData.getString("morph")).getName().equals(c.getClass().getName()))
                                .map(MorphCollectible.class::cast)
                                .findFirst()
                                .ifPresent(this::activateMorph);

                        this.unlockedCollectibles.stream()
                                .filter(c -> !hubData.getString("particle").equals("none") && cm.getById(hubData.getString("particle")).getName().equals(c.getClass().getName()))
                                .map(ParticleCollectible.class::cast)
                                .findFirst()
                                .ifPresent(this::activateParticle);

                        this.unlockedCollectibles.stream()
                                .filter(c -> !hubData.getString("gadget").equals("none") && cm.getById(hubData.getString("gadget")).getName().equals(c.getClass().getName()))
                                .map(GadgetCollectible.class::cast)
                                .findFirst()
                                .ifPresent(this::activateGadget);

                        Document parkourData = (Document) hubData.get("parkour");

                        if(parkourData != null)
                            parkourData.forEach((k, v) -> parkourCourseData.put(k, (int) v));
                    }

                    this.waterWarsData = new WaterWarsUserData(documents.getOrDefault(ServerGameType.WATER_WARS.toString(), null));
                })
                .execute(() -> loaded = true);
    }

    /**
     * GET UUID of {@link User}
     * @return UUID
     */
    public UUID getUuid() {
        return uuid;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public me.chasertw123.minigames.core.user.User getCoreUser() {
        return CoreAPI.getUser(Bukkit.getPlayer(uuid));
    }

    public Map<String, Integer> getParkourCourseData() {
        return parkourCourseData;
    }

    public boolean hasStartedCourse(UUID courseId) {
        return parkourCourseData.containsKey(courseId + "");
    }

    public UserCourseCompletionData getCourseData(UUID courseId) {
        return new UserCourseCompletionData(courseId, hasStartedCourse(courseId) ? parkourCourseData.get(courseId + "") : 0);
    }

    public boolean isLoaded() {
        return loaded;
    }

    public WaterWarsUserData getWaterWarsData() {
        return waterWarsData;
    }

    public MorphCollectible getActiveMorph() {
        return activeMorph;
    }

    public ParticleCollectible getActiveParticle() {
        return activeParticle;
    }

    public GadgetCollectible getActiveGadget() {
        return activeGadget;
    }

    public LoginMessage getSelectedLoginMessage() {
        return selectedLoginMessage;
    }

    public void setSelectedLoginMessage(LoginMessage selectedLoginMessage) {
        this.selectedLoginMessage = selectedLoginMessage;
    }

    public List<Collectible> getUnlockedCollectibles() {
        return unlockedCollectibles;
    }

    public boolean ownsCollectible(Class<? extends Collectible> clazz) {
        for (Collectible collectible : unlockedCollectibles)
            if (collectible.getClass().getName().equals(clazz.getName()))
                return true;

        return false;
    }

    public void unlockCollectible(Collectible collectible) {
        if (!this.ownsCollectible(collectible.getClass()))
            unlockedCollectibles.add(collectible);
    }

    public void activateGadget(GadgetCollectible gadgetCollectible) {

        if (activeGadget != null)
            disableGadget();

        this.activeGadget = gadgetCollectible;
        new AbstractItem(gadgetCollectible.getHandStack(), getCoreUser(), 5, (type) -> {
            if (type == AbstractItem.InteractType.RIGHT)
                gadgetCollectible.onRightClick();
            else if (type == AbstractItem.InteractType.LEFT)
                gadgetCollectible.onLeftClick();
        });
    }

    public void disableGadget() {
        this.activeGadget.onClear();
        this.activeGadget = null;
        this.getCoreUser().getAbstractItems().remove(5);
        this.getPlayer().getInventory().setItem(5, null);
    }

    public void activateMorph(MorphCollectible morphCollectible) {

        if (activeMorph != null)
            this.disableMorph();

        this.activeMorph = morphCollectible;
        this.activeMorph.spawn();
        this.activeMorph.updateEntityLocation();
        this.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0, false, false));
    }

    public void disableMorph() {
        this.activeMorph.kill();
        this.activeMorph = null;
        this.getCoreUser().getPlayer().removePotionEffect(PotionEffectType.INVISIBILITY);
    }

    public void activateParticle(ParticleCollectible particleCollectible) {

        if (activeParticle != null)
            this.disableParticle();

        this.activeParticle = particleCollectible;
        this.activeParticle.startLoop();
    }

    public void disableParticle() {
        this.activeParticle.cancelLoop();
        this.activeParticle = null;
    }

    public void updatePlayerVisibility() {

        boolean value = getCoreUser().getSetting(Setting.PLAYER_VISIBILITY);
        getCoreUser().togglePlayerVisibility(value);

        new AbstractItem(value ? Items.HIDE_PLAYERS.getItemStack() : Items.SHOW_PLAYERS.getItemStack(), getCoreUser(), 8, (type) ->{

            if (type != AbstractItem.InteractType.RIGHT)
                return;

            if ((lastVisibilityToggle + 4500) > System.currentTimeMillis()) {
                Title.sendActionbar(getPlayer(), ChatColor.YELLOW + "You must wait " + String.format("%.2f", ((lastVisibilityToggle + 4500) - System.currentTimeMillis()) / 1000.0F)
                        + "s before toggling player visibility again!");
                return;
            }

            getPlayer().sendMessage(value ? ChatColor.RED + "You have turned off the visibility of other players!" : ChatColor.GREEN + "You have turned on the visibility of other players!");
            getPlayer().playSound(getPlayer().getLocation(), Sound.SUCCESSFUL_HIT, 1.0F, value ? 0.5F : 1.5F);
            getCoreUser().togglePlayerVisibility(!value);
            getCoreUser().setSetting(Setting.PLAYER_VISIBILITY, !value);
            lastVisibilityToggle = System.currentTimeMillis();
            updatePlayerVisibility();
        });
    }

    public boolean save() {

        if (!loaded)
            return true;

        Document hubData = new Document(), wwData = new Document(), unlocks = new Document();
        List<String> wwKits = new ArrayList<>(), wwCages = new ArrayList<>();

        unlockedCollectibles.forEach(obj -> unlocks.put(me.chasertw123.minigames.core.Main.getCollectibleManager().getId(obj.getClass()), "INSERT DATA HERE"));
        hubData.append("uuid", uuid.toString())
                .append("unlocks", unlocks)
                .append("morph", activeMorph == null ? "none" : me.chasertw123.minigames.core.Main.getCollectibleManager().getId(activeMorph.getClass()))
                .append("particle", activeParticle == null ? "none" : me.chasertw123.minigames.core.Main.getCollectibleManager().getId(activeParticle.getClass()))
                .append("gadget", activeGadget == null ? "none" : me.chasertw123.minigames.core.Main.getCollectibleManager().getId(activeGadget.getClass()));

        waterWarsData.getOwnedKits().forEach(obj -> wwKits.add(obj.toString()));
        waterWarsData.getOwnedCages().forEach(obj -> wwCages.add(obj.toString()));
        wwData.append("uuid", uuid.toString())
                .append("kit", waterWarsData.getSelectedKit().toString())
                .append("cage", waterWarsData.getSelectedCage().toString())
                .append("kits", wwKits)
                .append("cages", wwCages);


        Document parkourData = new Document();
        this.parkourCourseData.forEach(parkourData::put);
        hubData.put("parkour", parkourData);

        Database database = CoreAPI.getDatabase();

        UpdateResult result1 = database.getMongoCollection(Database.Collection.HUB_USER)
                .replaceOne(Filters.eq("uuid", uuid.toString()), hubData, Database.upsert());

        UpdateResult result2 = database.getMongoCollection(ServerGameType.WATER_WARS)
                .replaceOne(Filters.eq("uuid", uuid.toString()), wwData, Database.upsert());

        return result1.getModifiedCount() >= result1.getMatchedCount() && result2.getModifiedCount() >= result2.getMatchedCount();
    }
}
