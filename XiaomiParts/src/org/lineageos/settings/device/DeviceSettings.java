/*
 * Copyright (C) 2018-2019 The Xiaomi-MSM8953 Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package org.lineageos.settings.device;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.preference.PreferenceFragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;
import android.content.SharedPreferences;

import org.lineageos.settings.device.kcal.KCalSettingsActivity;
import org.lineageos.settings.device.FPSInfoService;
import org.lineageos.settings.device.preferences.SecureSettingListPreference;
import org.lineageos.settings.device.preferences.SecureSettingSwitchPreference;
import org.lineageos.settings.device.preferences.VibrationSeekBarPreference;
import org.lineageos.settings.device.preferences.NotificationLedSeekBarPreference;
import org.lineageos.settings.device.preferences.CustomSeekBarPreference;

import java.lang.Math.*;

public class DeviceSettings extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    public static final String PREF_FLASH0_BRIGHTNESS = "flash0_brightness";
    public static final String PREF_FLASH1_BRIGHTNESS = "flash1_brightness";
    public static final String FLASH0_BRIGHTNESS_PATH = "/sys/class/leds/led:torch_0/max_brightness";
    public static final String FLASH1_BRIGHTNESS_PATH = "/sys/class/leds/led:torch_1/max_brightness";

    public static final String CATEGORY_VIBRATOR = "vibration";
    public static final String PREF_VIBRATION_STRENGTH = "vibration_strength";
    public static final String VIBRATION_STRENGTH_PATH = "/sys/devices/virtual/timed_output/vibrator/vtg_level";

    public static final String CATEGORY_NOTIF = "notification_led";
    public static final String PREF_NOTIF_LED = "notification_led_brightness";
    public static final String NOTIF_LED_PATH = "/sys/class/leds/red/max_brightness";

    //public static final String PREF_HEADPHONE_GAIN = "headphone_gain";
    //public static final String HEADPHONE_GAIN_PATH = "/sys/kernel/sound_control/headphone_gain";
    //public static final String PREF_MIC_GAIN = "mic_gain";
    //public static final String MIC_GAIN_PATH = "/sys/kernel/sound_control/mic_gain";

    public static final String PREF_KEY_FPS_INFO = "fps_info";

    // value of vtg_min and vtg_max
    public static final int MIN_VIBRATION = 816;  //1016=1,016v
    public static final int MAX_VIBRATION = 3596; //3596=3.596v

    public static final int MIN_LED = 1;
    public static final int MAX_LED = 255;

    private static final String CATEGORY_DISPLAY = "display";
    private static final String PREF_DEVICE_KCAL = "device_kcal";

    private static final String PREF_ENABLE_DIRAC = "dirac_enabled";
    private static final String PREF_HEADSET = "dirac_headset_pref";
    private static final String PREF_PRESET = "dirac_preset_pref";

    private static Context mContext;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences_xiaomi_parts, rootKey);

        mContext = this.getContext();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mContext);
        // Flash white
        CustomSeekBarPreference flash0Gain = (CustomSeekBarPreference) findPreference(PREF_FLASH0_BRIGHTNESS);
         if (FileUtils.fileWritable(FLASH0_BRIGHTNESS_PATH)) {
          flash0Gain.setOnPreferenceChangeListener(this);
         } else {
        getPreferenceScreen().removePreference(flash0Gain);
        }
        // Flash yellow
        CustomSeekBarPreference flash1Gain = (CustomSeekBarPreference) findPreference(PREF_FLASH1_BRIGHTNESS);
         if (FileUtils.fileWritable(FLASH1_BRIGHTNESS_PATH)) {
          flash1Gain.setOnPreferenceChangeListener(this);
         } else {
        getPreferenceScreen().removePreference(flash1Gain);
        }
        // Led
        if (FileUtils.fileWritable(NOTIF_LED_PATH)) {
            NotificationLedSeekBarPreference notifLedBrightness =
                    (NotificationLedSeekBarPreference) findPreference(PREF_NOTIF_LED);
            notifLedBrightness.setOnPreferenceChangeListener(this);
        } else { getPreferenceScreen().removePreference(findPreference(CATEGORY_NOTIF)); }
        // Vibration
        if (FileUtils.fileWritable(VIBRATION_STRENGTH_PATH)) {
            VibrationSeekBarPreference vibrationStrength = (VibrationSeekBarPreference) findPreference(PREF_VIBRATION_STRENGTH);
            vibrationStrength.setOnPreferenceChangeListener(this);
        } else { getPreferenceScreen().removePreference(findPreference(CATEGORY_VIBRATOR)); }

        // Headphone Gain
        //CustomSeekBarPreference headphoneGain = (CustomSeekBarPreference) findPreference(PREF_HEADPHONE_GAIN);
        //if (FileUtils.fileWritable(HEADPHONE_GAIN_PATH)) {
           //headphoneGain.setOnPreferenceChangeListener(this);
        //} else {
          //getPreferenceScreen().removePreference(headphoneGain);
        //}

        // Mic Gain
        //CustomSeekBarPreference micGain = (CustomSeekBarPreference) findPreference(PREF_MIC_GAIN);
         //if (FileUtils.fileWritable(MIC_GAIN_PATH)) {
          //micGain.setOnPreferenceChangeListener(this);
         //} else {
        //getPreferenceScreen().removePreference(micGain);
        //}

        PreferenceCategory displayCategory = (PreferenceCategory) findPreference(CATEGORY_DISPLAY);
        Preference kcal = findPreference(PREF_DEVICE_KCAL);

        kcal.setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), KCalSettingsActivity.class);
            startActivity(intent);
            return true;
        });

        boolean enhancerEnabled;
        try {
            enhancerEnabled = DiracService.sDiracUtils.isDiracEnabled();
        } catch (java.lang.NullPointerException e) {
            getContext().startService(new Intent(getContext(), DiracService.class));
            try {
                enhancerEnabled = DiracService.sDiracUtils.isDiracEnabled();
            } catch (NullPointerException ne) {
                // Avoid crash
                ne.printStackTrace();
                enhancerEnabled = false;
            }
        }

        SwitchPreference fpsInfo = (SwitchPreference) findPreference(PREF_KEY_FPS_INFO);
        fpsInfo.setChecked(prefs.getBoolean(PREF_KEY_FPS_INFO, false));
        fpsInfo.setOnPreferenceChangeListener(this);

        SecureSettingSwitchPreference enableDirac = (SecureSettingSwitchPreference) findPreference(PREF_ENABLE_DIRAC);
        enableDirac.setOnPreferenceChangeListener(this);
        enableDirac.setChecked(enhancerEnabled);

        SecureSettingListPreference headsetType = (SecureSettingListPreference) findPreference(PREF_HEADSET);
        headsetType.setOnPreferenceChangeListener(this);

        SecureSettingListPreference preset = (SecureSettingListPreference) findPreference(PREF_PRESET);
        preset.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        final String key = preference.getKey();
        switch (key) {
            case PREF_FLASH0_BRIGHTNESS:
                FileUtils.setValue(FLASH0_BRIGHTNESS_PATH, (int) value);
                break;

            case PREF_FLASH1_BRIGHTNESS:
                FileUtils.setValue(FLASH1_BRIGHTNESS_PATH, (int) value);
                break;

            case PREF_NOTIF_LED:
                FileUtils.setValue(NOTIF_LED_PATH, (1 + Math.pow(1.05694, (int) value )));
                break;

            case PREF_VIBRATION_STRENGTH:
                double vibrationValue = (int) value / 100.0 * (MAX_VIBRATION - MIN_VIBRATION) + MIN_VIBRATION;
                FileUtils.setValue(VIBRATION_STRENGTH_PATH, vibrationValue);
                break;

            case PREF_KEY_FPS_INFO:
                boolean enabled = (Boolean) value;
                Intent fpsinfo = new Intent(this.getContext(), FPSInfoService.class);
                if (enabled) {
                    this.getContext().startService(fpsinfo);
                } else {
                    this.getContext().stopService(fpsinfo);
                }
                break;

            //case PREF_HEADPHONE_GAIN:
                //FileUtils.setValue(HEADPHONE_GAIN_PATH, value + " " + value);
                //break;

            //case PREF_MIC_GAIN:
                //FileUtils.setValue(MIC_GAIN_PATH, (int) value);
                //break;

            case PREF_ENABLE_DIRAC:
                try {
                    DiracService.sDiracUtils.setEnabled((boolean) value);
                } catch (java.lang.NullPointerException e) {
                    getContext().startService(new Intent(getContext(), DiracService.class));
                    DiracService.sDiracUtils.setEnabled((boolean) value);
                }
                break;

            case PREF_HEADSET:
                try {
                    DiracService.sDiracUtils.setHeadsetType(Integer.parseInt(value.toString()));
                } catch (java.lang.NullPointerException e) {
                    getContext().startService(new Intent(getContext(), DiracService.class));
                    DiracService.sDiracUtils.setHeadsetType(Integer.parseInt(value.toString()));
                }
                break;

            case PREF_PRESET:
                try {
                    DiracService.sDiracUtils.setLevel(String.valueOf(value));
                } catch (java.lang.NullPointerException e) {
                    getContext().startService(new Intent(getContext(), DiracService.class));
                    DiracService.sDiracUtils.setLevel(String.valueOf(value));
                }
                break;

            default:
                break;
        }
        return true;
    }
}
