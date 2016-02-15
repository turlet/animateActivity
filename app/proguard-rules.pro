-optimizationpasses 5
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-dontpreverify
-verbose
-ignorewarnings
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!method/removal/*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

-dontwarn com.google.android.gms.*

-dontwarn android.support.**

-printmapping ./mapping.txt

-dontwarn com.appsflyer.**

-keep class com.appsflyer.** { *;}

-dontwarn **CompatHoneycomb
-dontwarn **CompatHoneycombMR2
-dontwarn **CompatCreatorHoneycombMR2


-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
-keepattributes JavascriptInterface

-keep class com.mobile.indiapp.fragment.UcoinExchangeFragment$WebAppInterface

-keepclassmembers class com.mobile.indiapp.fragment.UcoinExchangeFragment$WebAppInterface {
    <methods>;
}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
-keepattributes *Annotation*


# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.mobile.indiapp.bean.** { *; }

##---------------End: proguard configuration for Gson  ----------
-keepattributes Exceptions,InnerClasses,...
-keep class com.lib.common.tool.PPIncrementalUpdate { *; }
-keep class com.lib.common.tool.PPIncrementalUpdate$ZffRffBean { *; }
#-keep  class com.lib.common.tool.PPIncrementalUpdate$ZffRffBean { *;}

##---------------End: proguard configuration for Glide  ----------
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
    **[] $VALUES;
    public *;
}
#