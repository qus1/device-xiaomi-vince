# ART
PRODUCT_PROPERTY_OVERRIDES += \
dalvik.vm.dex2oat-filter=speed \
dalvik.vm.image-dex2oat-filter=speed

# Audio fluence, voice and media volume steps
PRODUCT_PROPERTY_OVERRIDES += \
persist.vendor.audio.fluence.speaker=true \
persist.vendor.audio.fluence.voicecall=true \
persist.vendor.audio.fluence.voicerec=false \
ro.vendor.audio.sdk.fluencetype=none \
ro.config.media_vol_steps=25 \
ro.config.vc_call_vol_steps=7

# Audio workarounds
PRODUCT_PROPERTY_OVERRIDES += \
debug.stagefright.omx_default_rank.sw-audio=1 \
debug.stagefright.omx_default_rank=0

# Audio
PRODUCT_PROPERTY_OVERRIDES += \
audio.chk.cal.us=0 \
af.fast_track_multiplier=1 \
audio.deep_buffer.media=true \
audio.offload.disable=false \
audio.offload.min.duration.secs=30 \
audio.offload.video=true \
persist.vendor.audio.hw.binder.size_kbyte=1024 \
persist.vendor.audio.speaker.prot.enable=false \
ro.vendor.audio.sdk.ssr=false \
vendor.audio.dolby.ds2.enabled=false \
vendor.audio.dolby.ds2.hardbypass=false \
vendor.audio.flac.sw.decoder.24bit=true \
vendor.audio.read.wsatz.type=true \
ro.af.client_heap_size_kbyte=7168 \
vendor.audio.hw.aac.encoder=true \
vendor.audio.offload.buffer.size.kb=64 \
vendor.audio.offload.gapless.enabled=true \
vendor.audio.offload.multiaac.enable=true \
vendor.audio.offload.multiple.enabled=false \
vendor.audio.offload.passthrough=false \
vendor.audio.offload.track.enable=false \
vendor.audio.parser.ip.buffer.size=262144 \
vendor.audio.playback.mch.downsample=true \
vendor.audio.pp.asphere.enabled=false \
vendor.audio.safx.pbe.enabled=true \
vendor.audio.tunnel.encode=false \
vendor.audio.use.sw.alac.decoder=true \
vendor.audio.use.sw.ape.decoder=true \
vendor.audio_hal.period_size=192 \
vendor.voice.conc.fallbackpath=deep-buffer \
vendor.voice.path.for.pcm.voip=true \
vendor.voice.playback.conc.disabled=true \
vendor.voice.record.conc.disabled=false \
vendor.voice.voip.conc.disabled=true

# Bluetooth
PRODUCT_PROPERTY_OVERRIDES += \
persist.bluetooth.bluetooth_audio_hal.disabled=true \
vendor.qcom.bluetooth.soc=smd \
ro.bluetooth.hfp.ver=1.7 \
ro.qualcomm.bt.hci_transport=smd \
persist.vendor.bt.aac_frm_ctl.enabled=true

# Camera
PRODUCT_PROPERTY_OVERRIDES += \
camera.lowpower.record.enable=1 \
media.camera.ts.monotonic=1 \
dalvik.vm.heapstartsize=16m \
dalvik.vm.heapgrowthlimit=256m \
dalvik.vm.heapsize=512m \
dalvik.vm.heaptargetutilization=0.75 \
dalvik.vm.heapminfree=4m \
dalvik.vm.heapmaxfree=8m \
persist.camera.CDS=off \
persist.camera.video.CDS=off \
persist.camera.dual.camera=0 \
persist.camera.gyro.disable=0 \
persist.camera.isp.clock.optmz=0 \
persist.camera.stats.test=5 \
persist.vendor.qti.telephony.vt_cam_interface=1 \
#persist.camera.HAL3.enabled=1
#persist.camera.eis.enable=1

#add for dirac algo tsx 9/12
PRODUCT_PROPERTY_OVERRIDES += \
persist.dirac.acs.controller=qem \
persist.dirac.acs.storeSettings=1 \
persist.dirac.acs.ignore_error=1 \
persist.audio.dirac.speaker=true \
ro.audio.soundfx.dirac=true

# Display
PRODUCT_PROPERTY_OVERRIDES += \
ro.opengles.version=196610 \
ro.hardware.vulkan=adreno \
ro.hardware.egl=adreno
#debug.sdm.support_writeback=0

# DRM
PRODUCT_PROPERTY_OVERRIDES += \
drm.service.enabled=true

# Fm
PRODUCT_PROPERTY_OVERRIDES += \
vendor.hw.fm.init=0

# Frp
PRODUCT_PROPERTY_OVERRIDES += \
ro.frp.pst=/dev/block/bootdevice/by-name/config

# IMS debug
#PRODUCT_PROPERTY_OVERRIDES += \
#persist.vendor.ims.disableADBLogs=1 \
#persist.vendor.ims.disableDebugLogs=1 \
#persist.vendor.ims.disableIMSLogs=1 \
#persist.vendor.ims.disableQXDMLogs=1

# Perf
PRODUCT_PROPERTY_OVERRIDES += \
ro.sys.fw.dex2oat_thread_count=8 \
ro.vendor.extension_library=libqti-perfd-client.so

# Radio
PRODUCT_PROPERTY_OVERRIDES += \
persist.radio.csvt.enabled=false \
persist.radio.jbims=0 \
persist.radio.mt_sms_ack=20 \
persist.radio.multisim.config=dsds \
persist.vendor.qcomsysd.enabled=1 \
persist.radio.videopause.mode=1 \
persist.vendor.radio.apm_sim_not_pwdn=1 \
persist.vendor.radio.custom_ecc=1 \
persist.vendor.radio.hw_mbn_update=0 \
persist.vendor.radio.rat_on=combine \
persist.vendor.radio.sw_mbn_update=0 \
persist.vendor.radio.sib16_support=1 \
rild.libpath=/vendor/lib64/libril-qc-qmi-1.so \
vendor.rild.libpath=/vendor/lib64/libril-qc-qmi-1.so \
ro.telephony.call_ring.multiple=false \
service.qti.ims.enabled=1 \
persist.vendor.radio.data_con_rprt=1 \
ro.telephony.iwlan_operation_mode=legacy \
persist.sys.fflag.override.settings_network_and_internet_v2=true
#persist.vendor.radio.aosp_usr_pref_sel=true

# VOLTE & VOWIFI, RCS issues
PRODUCT_PROPERTY_OVERRIDES += \
persist.dbg.ims_volte_enable=1 \
persist.dbg.volte_avail_ovr=1 \
persist.dbg.vt_avail_ovr=1 \
persist.dbg.wfc_avail_ovr=0 \
persist.radio.VT_ENABLE=1 \
persist.radio.calls.on.ims=0 \
persist.radio.volte.dan_support=true \
persist.data.iwlan.enable=true \
persist.data.iwlan=1 \
persist.data.iwlan.ipsec.ap=1 \
persist.sys.cust.lte_config=true \
persist.rcs.supported=1

# Usb
PRODUCT_PROPERTY_OVERRIDES += \
persist.vendor.usb.config.extra=none

# Boot
#PRODUCT_PROPERTY_OVERRIDES += \
#sys.vendor.shutdown.waittime=500

# Time Services
#PRODUCT_PROPERTY_OVERRIDES += \
#persist.vendor.delta_time.enable=true \
#persist.delta_time.enable=true

# Rescue party
#PRODUCT_PROPERTY_OVERRIDES += \
#persist.sys.disable_rescue=true

# Delayed sound issues
#PRODUCT_PROPERTY_OVERRIDES += \
#media.stagefright.audio.sink=280 \
#audio.heap.size.multiplier=7
