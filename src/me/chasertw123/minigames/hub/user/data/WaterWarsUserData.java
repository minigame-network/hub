package me.chasertw123.minigames.hub.user.data;

import me.chasertw123.minigames.wws.unlocks.cages.Cage;
import me.chasertw123.minigames.wws.unlocks.kits.Kit;
import me.chasertw123.minigames.wws.users.WaterWarsUser;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class WaterWarsUserData implements WaterWarsUser {

    private Kit selectedKit;
    private Cage selectedCage;

    private List<Kit> ownedKits = new ArrayList<>();
    private List<Cage> ownedCages = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public WaterWarsUserData(Document waterWarsData) {

        if (waterWarsData != null) {
            ((List<String>) waterWarsData.get("kits")).forEach(obj -> this.ownedKits.add(Kit.valueOf(obj)));
            ((List<String>) waterWarsData.get("cages")).forEach(obj -> this.ownedCages.add(Cage.valueOf(obj)));
            this.selectedKit = Kit.valueOf(waterWarsData.getString("kit"));
            this.selectedCage = Cage.valueOf(waterWarsData.getString("cage"));
            return;
        }

        this.selectedKit = Kit.WOODSMAN;
        this.selectedCage = Cage.GLASS;
        this.ownedKits.add(Kit.WOODSMAN);
        this.ownedCages.add(Cage.GLASS);
    }

    public Kit getSelectedKit() {
        return selectedKit;
    }

    public void setSelectedKit(Kit selectedKit) {
        this.selectedKit = selectedKit;

        if (!ownsKit(selectedKit))
            ownedKits.add(selectedKit);
    }

    public boolean ownsKit(Kit kit) {
        return ownedKits.contains(kit);
    }

    public Cage getSelectedCage() {
        return selectedCage;
    }

    public void setSelectedCage(Cage selectedCage) {
        this.selectedCage = selectedCage;

        if (!ownsCage(selectedCage))
            ownedCages.add(selectedCage);
    }

    public boolean ownsCage(Cage cage) {
        return ownedCages.contains(cage);
    }

    public List<Kit> getOwnedKits() {
        return ownedKits;
    }

    public List<Cage> getOwnedCages() {
        return ownedCages;
    }
}
