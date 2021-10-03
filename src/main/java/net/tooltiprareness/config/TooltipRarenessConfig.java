package net.tooltiprareness.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = "tooltiprareness")
@Config.Gui.Background("minecraft:textures/block/stone.png")
public class TooltipRarenessConfig implements ConfigData {

    public int test = 0;
    public int test2 = 0xFF002A00;

    public boolean showRarenessTooltip = true;
    public boolean showFrameColor = true;

    @ConfigEntry.Category("advanced_settings")
    public int common_frame_color = 0xFFAAAAAA;
    @ConfigEntry.Category("advanced_settings")
    public int uncommon_frame_color = 0xFF00AA00;
    @ConfigEntry.Category("advanced_settings")
    public int rare_frame_color = 0xFFFF5555;
    @ConfigEntry.Category("advanced_settings")
    public int epic_frame_color = 0xFFAA00AA;
    @ConfigEntry.Category("advanced_settings")
    public int legendary_frame_color = 0xFFFFAA00;
    @ConfigEntry.Category("advanced_settings")
    public int administrator_frame_color = 0xFF555555;

    @ConfigEntry.Category("advanced_settings")
    public int common_second_frame_color = 0xFF2A2A2A;
    @ConfigEntry.Category("advanced_settings")
    public int uncommon_second_frame_color = 0xFF002A00;
    @ConfigEntry.Category("advanced_settings")
    public int rare_second_frame_color = 0xFF421515;
    @ConfigEntry.Category("advanced_settings")
    public int epic_second_frame_color = 0xFF2A002A;
    @ConfigEntry.Category("advanced_settings")
    public int legendary_second_frame_color = 0xFF2A2A00;
    @ConfigEntry.Category("advanced_settings")
    public int administrator_second_frame_color = 0xFF151515;

    @ConfigEntry.Category("advanced_settings")
    @Comment("String length is used for frame color")
    public int common_string_length = 36;
    @ConfigEntry.Category("advanced_settings")
    public int uncommon_string_length = 48;
    @ConfigEntry.Category("advanced_settings")
    public int rare_string_length = 24;
    @ConfigEntry.Category("advanced_settings")
    public int epic_string_length = 20;
    @ConfigEntry.Category("advanced_settings")
    public int legendary_string_length = 54;
    @ConfigEntry.Category("advanced_settings")
    public int administrator_string_length = 66;
    @ConfigEntry.Category("advanced_settings")
    public boolean changeBackgroundColor = false;
    @ConfigEntry.Category("advanced_settings")
    public int backgroundColor = -267386864;

}