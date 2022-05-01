package op.mobile.app.dev.graysono.travelling.model;

import java.lang.System;

/**
 * Unlike strings, integers and booleans, user defined objects can not be passed
 * between Fragments.
 *
 * Resource: https://developer.android.com/kotlin/parcelize
 */
@kotlinx.parcelize.Parcelize()
@kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001Ba\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0011\u0010\b\u001a\r\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\b\u000b\u0012\u0006\u0010\f\u001a\u00020\u0005\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u0012\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u000f0\t\u00a2\u0006\u0002\b\u000b\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\u0014\u0010 \u001a\r\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\b\u000bH\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u00c6\u0003J\u0014\u0010#\u001a\r\u0012\u0004\u0012\u00020\u000f0\t\u00a2\u0006\u0002\b\u000bH\u00c6\u0003Ju\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\u0013\b\u0002\u0010\b\u001a\r\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\b\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00052\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0013\b\u0002\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u000f0\t\u00a2\u0006\u0002\b\u000bH\u00c6\u0001J\t\u0010%\u001a\u00020\u0003H\u00d6\u0001J\u0013\u0010&\u001a\u00020\'2\b\u0010(\u001a\u0004\u0018\u00010)H\u00d6\u0003J\t\u0010*\u001a\u00020\u0003H\u00d6\u0001J\t\u0010+\u001a\u00020\u0005H\u00d6\u0001J\u0019\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u0003H\u00d6\u0001R\u001c\u0010\b\u001a\r\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\u0002\b\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0014R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u001c\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u000f0\t\u00a2\u0006\u0002\b\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012\u00a8\u00061"}, d2 = {"Lop/mobile/app/dev/graysono/travelling/model/Country;", "Landroid/os/Parcelable;", "id", "", "name", "", "capital", "flagImg", "attractions", "", "Lop/mobile/app/dev/graysono/travelling/model/Attraction;", "Lkotlinx/parcelize/RawValue;", "langCode", "phrases", "quiz", "Lop/mobile/app/dev/graysono/travelling/model/Quiz;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/util/List;Ljava/util/List;)V", "getAttractions", "()Ljava/util/List;", "getCapital", "()Ljava/lang/String;", "getFlagImg", "getId", "()I", "getLangCode", "getName", "getPhrases", "getQuiz", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_debug"})
public final class Country implements android.os.Parcelable {
    private final int id = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String capital = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String flagImg = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<op.mobile.app.dev.graysono.travelling.model.Attraction> attractions = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String langCode = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> phrases = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<op.mobile.app.dev.graysono.travelling.model.Quiz> quiz = null;
    public static final android.os.Parcelable.Creator<op.mobile.app.dev.graysono.travelling.model.Country> CREATOR = null;
    
    public final int getId() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCapital() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFlagImg() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<op.mobile.app.dev.graysono.travelling.model.Attraction> getAttractions() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLangCode() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getPhrases() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<op.mobile.app.dev.graysono.travelling.model.Quiz> getQuiz() {
        return null;
    }
    
    public Country(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String capital, @org.jetbrains.annotations.NotNull()
    java.lang.String flagImg, @org.jetbrains.annotations.NotNull()
    java.util.List<op.mobile.app.dev.graysono.travelling.model.Attraction> attractions, @org.jetbrains.annotations.NotNull()
    java.lang.String langCode, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> phrases, @org.jetbrains.annotations.NotNull()
    java.util.List<op.mobile.app.dev.graysono.travelling.model.Quiz> quiz) {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<op.mobile.app.dev.graysono.travelling.model.Attraction> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<op.mobile.app.dev.graysono.travelling.model.Quiz> component8() {
        return null;
    }
    
    /**
     * Unlike strings, integers and booleans, user defined objects can not be passed
     * between Fragments.
     *
     * Resource: https://developer.android.com/kotlin/parcelize
     */
    @org.jetbrains.annotations.NotNull()
    public final op.mobile.app.dev.graysono.travelling.model.Country copy(int id, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String capital, @org.jetbrains.annotations.NotNull()
    java.lang.String flagImg, @org.jetbrains.annotations.NotNull()
    java.util.List<op.mobile.app.dev.graysono.travelling.model.Attraction> attractions, @org.jetbrains.annotations.NotNull()
    java.lang.String langCode, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> phrases, @org.jetbrains.annotations.NotNull()
    java.util.List<op.mobile.app.dev.graysono.travelling.model.Quiz> quiz) {
        return null;
    }
    
    /**
     * Unlike strings, integers and booleans, user defined objects can not be passed
     * between Fragments.
     *
     * Resource: https://developer.android.com/kotlin/parcelize
     */
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    /**
     * Unlike strings, integers and booleans, user defined objects can not be passed
     * between Fragments.
     *
     * Resource: https://developer.android.com/kotlin/parcelize
     */
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    /**
     * Unlike strings, integers and booleans, user defined objects can not be passed
     * between Fragments.
     *
     * Resource: https://developer.android.com/kotlin/parcelize
     */
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
    
    @java.lang.Override()
    public int describeContents() {
        return 0;
    }
    
    @java.lang.Override()
    public void writeToParcel(@org.jetbrains.annotations.NotNull()
    android.os.Parcel parcel, int flags) {
    }
    
    @kotlin.Metadata(mv = {1, 4, 2}, bv = {1, 0, 3}, k = 3)
    public static final class Creator implements android.os.Parcelable.Creator<op.mobile.app.dev.graysono.travelling.model.Country> {
        
        public Creator() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final op.mobile.app.dev.graysono.travelling.model.Country[] newArray(int size) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        @java.lang.Override()
        public final op.mobile.app.dev.graysono.travelling.model.Country createFromParcel(@org.jetbrains.annotations.NotNull()
        android.os.Parcel in) {
            return null;
        }
    }
}