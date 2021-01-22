LOCAL_PATH := $(call my-dir)

#https://www.celsoazevedo.com/files/android/google-camera/ar/
include $(CLEAR_VARS)
LOCAL_MODULE := GooglePlayground
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := GooglePlayground/GooglePlayground.apk
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := .apk
LOCAL_MODULE_PATH := $(TARGET_OUT)/product/app
LOCAL_PRODUCT_MODULE := true
include $(BUILD_PREBUILT)

#services from /vendor/gapps/system/product/app
include $(CLEAR_VARS)
LOCAL_MODULE := arcore
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := arcore/arcore.apk
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_SUFFIX := .apk
LOCAL_MODULE_PATH := $(TARGET_OUT)/product/app
LOCAL_PRODUCT_MODULE := true
include $(BUILD_PREBUILT)
