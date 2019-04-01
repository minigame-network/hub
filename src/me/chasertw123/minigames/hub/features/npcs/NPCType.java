package me.chasertw123.minigames.hub.features.npcs;

import me.chasertw123.minigames.hub.features.guis.mysteryman.GUI_MysteryMan;
import me.chasertw123.minigames.hub.user.User;
import me.chasertw123.minigames.shared.framework.ServerGameType;
import org.bukkit.ChatColor;

public enum NPCType {

//    WATER_WATERS_SOLO,
//    WATER_WATERS_DUOS,
//    WATER_WARS_THREES,
//    WATER_WARS_QUADS,
//    EVOLUTION,
//    MCPARTY,
    SPLEGG(new String[] {ChatColor.GOLD + "" + ChatColor.BOLD + "Splegg", ChatColor.YELLOW + "Punch to Play!", ChatColor.AQUA + "0 Playing"},
        "eyJ0aW1lc3RhbXAiOjE1MzE1Mzk1NTU2NjYsInByb2ZpbGVJZCI6IjkyZGVhZmE5NDMwNzQyZDliMDAzODg2MDE1OThkNmMwIiwicHJvZmlsZU5hbWUiOiJNSEZfQ2hpY2tlbiIsInNpZ25hdHVyZVJlcXVpcmVkIjp0cnVlLCJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTE2YjhlOTgzODljNTQxYmIzNjQ1Mzg1MGJjYmQxZjdiYzVhNTdkYTYyZGNjNTA1MDYwNDA5NzM3ZWM1YjcyYSJ9fX0=",
        "Yv5MCasDX/+VMTeIdau1m6QBIyrQn5Yl3Pa2QF7zg9Mg5iOHs/lgs7mc+WbWhOhxhc4paRoRwxOx610DHozZ3uETswz6xdg5T3cc5JEJcrA8p3HQCoRTlQFDpwf+lQvDr60VTSPkflHZDCtl5DYdIz17KmVlj4yPNwbMtwFGe+fPlC7XSLVgNDLeUJ8ZZg1dqGAXII3k0zpXtJ1OMTQeXKfVJzJ7bssN8zflHLbTq46TGlynTOguoj0ZVrPlfdW6RPt3FIEW+B9Tnb707sOCmHpNJ+pspcGSBsVbAOX8opnpq3Nj8m8pQDWK7ZZR+dpB8v9Jt+os0ZzNW0KTB54P8CehR/mZyLHmdL4Grfb0lclD7pscABfuwwXbBwHlg25lHQm2AItGLVbgFsYtN90wHlfJs+OQF/FGgzv3gl89l/cAmJktEcOPnj7ryLum/gcW9Uqc1EUgtAutDVLOPzJ+HntfwK8oLDKEJgx+Ed4EpwVrRNHIM9SxpPOaXlYwe0Sz91ZeFiAFeLwRzGoxUPx7bt4X8/zLKFVSIY/XmlFLGvViLjc3Cp/sqOtyNiWh9SSgyEeeMW9cnnOoksS9C/syTaH8gLjZtxclcCBfZDw5MvQcWJy7siGEtQKHjDqLlnDy5Mn3lH6JGh6zia4pOjhohb7tkVz4DwLEmlaWjHxz8Gg=",
        (user) -> sendToMinigame(user, ServerGameType.SPLEGG)),
    EVOLUTION(new String[] {ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Evolution", ChatColor.YELLOW + "Punch to Play!", ChatColor.AQUA + "0 Playing"},
        "eyJ0aW1lc3RhbXAiOjE1NDE5NDM1ODQyMjUsInByb2ZpbGVJZCI6IjIzZjFhNTlmNDY5YjQzZGRiZGI1MzdiZmVjMTA0NzFmIiwicHJvZmlsZU5hbWUiOiIyODA3Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS84MzI2MzYxMTE5ZDU0ZjNlN2NkMjU3NjU5YTc3YTI2NzdjNWY4Y2YzM2FjZjY5YTg0ZGJlNjRiNDA0ZTAxNzMzIn19fQ==",
        "WkKutTitR+ahOPvO3vDNcRyEC4y9M/cY1wpuaHLH+UB5fIl9sqI0BHc8G17xQdQTNqVEQwiSUTMqmlGcO+dHYC4pfRbqhh0FlwpLI0TTJk4mfdtKUhYlemx/EKlY8BY1PwFnsfaZpB13XMUOLMS5fFP3Kinwj+YhB15/TZWgB2bqr1/zzCZFDvJkekzjyH6A0Xb2dqcrrq0pNF5DH29KmLNZvAYsjAi9b1SNqnLtkM8NJoGNQeA1zQHhZtDauWWOIRugtY3naT8iRjzPupAB1fTLkku90mKwkqhyc6OQ5ecGtGXkwTRDHNw4sw7W+pcOjgSzwGAzy7DAnBH/xVUkM0Vx/SAJHZPU0rHjnlPu7r2CM+eJgl6eirb4FiNUUMXbOgJzZ5BBIGtus3trX0Pdso2tMpCrXdWmlLi0KbX7PO8BjPMN0SD2vsD1Yx2FJPvY9YMbtfXvxw9L5uDuiLEveDv5oheJkFkNen0ByCU6mYs25Av7cA8ONNVcN+Xtp3Kx3mf12b+cjZraAuBbENlCg20vBtsZCTxfqdmDeq2H9aBNPL7Lbjap22GOSqriR2SJ8uthJU+Fxadu0otm2LCKSvk3KxOCdWgKLfG4L4fcVEzl8n1uSVmbxmPM7CFR5ezyFNIl+TQDskHI+PB3t5i+n5uq5//4uG2EZNsTBZZPRPQ=",
        (user) -> sendToMinigame(user, ServerGameType.EVOLUTION)),
    WATER_WARS(new String[] {ChatColor.AQUA + "" + ChatColor.BOLD + "Water" + ChatColor.BLUE + "" + ChatColor.BOLD + "Wars", ChatColor.YELLOW + "Punch to Play!", ChatColor.AQUA + "0 Playing"},
        "eyJ0aW1lc3RhbXAiOjE1NDE5NDkyNTkzOTYsInByb2ZpbGVJZCI6IjQzYTgzNzNkNjQyOTQ1MTBhOWFhYjMwZjViM2NlYmIzIiwicHJvZmlsZU5hbWUiOiJTa3VsbENsaWVudFNraW42Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hZjljNTgwYTAxZTQwYTI2NWEwNjU1YWUwZjlkYzM4MmQyNDhjNzgwYmNmNjFlYWM2OTc5NjFhMTI5OTQzMmQifX19",
        "LMf7KbAzrEBZEmI+QrhaCvwvy8+rrDisLrXpRl/bYcms6BiV69Le+mK1YY3VMyS9vkPFHjQuwgGuilrKICR8iYVJjURjv6oUzxW9W92pEiRxzPZpoi3UODTIbEjb8+xBJA+83Jg29dhNMcRQ7jZQu5HnAH/MuHfwAllj8JZRHEWB6kYEFp0NWXcHjFcgkTnXxBdC5XzFRV2BBsZrKK407CQC7nZoKE9G9YjZCo+c9IX2xfSIpZzq617FvxRjVeuNOFDFizCUO14Qgcwg2nnKL+wA+I5Iq25IRIsFA1KROvsVQOfBwm3C0S6DRSS0b9Ukuge3G2v9L2R56iTyoC3z7t1k2A1Uvd7eixpN8zGrCwbd/dovZFA5pRfM/vmh5gBahGD+0E29Me1mvypmtrwXyTc2PmabLH1TA4WSrvArdnQy1Q0T0vDuZYs3luphAHzeKVwfyFRq1gWE81lIVU+XaTODgGCeUZO9jx5D86Shrd4BnKdMjCAnBg3SdNMfMh46EClgkf3mbfGChlMvnKvlt2ecRq7TIlMQwRIYtV3gFV2Z68TbmyBYIFKCXBA7mr5I9j864t1cIe4+F1w4WQ+zudp9dG+XOuuYOSYe73JtqWN5DpAdlKIHmBReh57nC3s/OS9mQnTtgflY7VWNXiWi939GUOZFH2VzvwTSSedrcGg=",
        (user) -> sendToMinigame(user, ServerGameType.WATER_WARS)),
    MYSTERY_MAN(new String[] {ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Mysterious Stranger", ChatColor.YELLOW + "Click for Rewards and Quests!"},
        "eyJ0aW1lc3RhbXAiOjE1MTcyNjc5ODc3MjIsInByb2ZpbGVJZCI6ImJkM2M2NDhiZDZhMDRmMDM5NDkzMjc1MzVjYjgzMWViIiwicHJvZmlsZU5hbWUiOiJFbW1hYVBsYXl6Iiwic2lnbmF0dXJlUmVxdWlyZWQiOnRydWUsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9iZjc4ZmNlYjUwYTk0ZDU3OWNlOWRkMzZmNTg1ZGU2YWI3YzVmNjA0OTRhOWM1ZGM5ZTQwMjQ0NDYyNGRkIn19fQ==",
        "uB5hh3gfIriK4HrqzeDstnuXqa7lIrDC+n5k7lvxMqC/Zk1UWokTKYG0cipbIhf/hge1oU5u0BRT+sZtBcUPUrYJshZiOPEhmPIkvYhndAY86Y4dZXlCvjwx2VWL5f5HbM1QbfbSQuzSf2Zh2WS7yjmCPKfbtBMHCkbeTFN9zntKdXm9DRvMOKwQ8eComOxHfUjO9az1TiUnuBGRRqPWx/NC523M0dtOre7yz3uXtiLsAvAVpLu4mp6EkSnVVRDyz0NYMLaIyrN4xdTW9FXJpb1x/mdNvJ2YdtVdDZLU2hud6NXw1PHBwT1431j/BdajofnbJ3TItxGpqhi7KpoMf6JwFRctdGsZnAtS9M+uUehNSenB40Kq2Eta7zhkG2x4e5x8hv1ybC0ilCZ5w5nNwEfUTw4UcnkE4tb6fr89K76JOOEbBo5rE+sxeuQI9NA5K4vST3wKNeadkJzDl6YOAgpGPgFnURjEa3IajtEwsnimhQxA0NMaj96dfqtBwR3CTFPYE9MRtPn2gtXGJSbA6w4r66nrsU3uuoNZ0hv3TAv2IDUKEvlJRdiCuSdStCC7Ycj+RF++o+OF04sYqPcdoH+P6LCHb3QAuACQhFm1Nva1JZTzq1+TfI1IfN6EwXGJbXJ4N1DJrgSUZrzSxxH/O4Upb3/IW+NLL5Lv5npZOYM=",
        GUI_MysteryMan::new);

    private String[] aboveText;
    private String skinValue, skinSignature;
    private NPCInteraction npcInteraction;

    NPCType(String[] aboveText, String skinValue, String skinSignature, NPCInteraction npcInteraction) {
        this.aboveText = aboveText;
        this.skinValue = skinValue;
        this.skinSignature = skinSignature;
        this.npcInteraction = npcInteraction;
    }

    public String[] getAboveText() {
        return aboveText;
    }

    public String getSkinValue() {
        return skinValue;
    }

    public String getSkinSignature() {
        return skinSignature;
    }

    public NPCInteraction getNPCInteraction() {
        return npcInteraction;
    }

    //

    public static void sendToMinigame(User user, ServerGameType serverGameType) {
        user.getCoreUser().addToQueue(serverGameType.toString().replaceAll("_", "-"));
    }

}
