package com.turlet.recyclerdemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：杨兴朗 on 2016/1/7 10:26
 * 邮箱：xinglang.yxl@alibaba-inc.com
 */
public class PageArgs implements Parcelable{

    private int x;
    private int y;

    public PageArgs() {
    }

    protected PageArgs(Parcel in) {
        x = in.readInt();
        y = in.readInt();
    }

    public static final Creator<PageArgs> CREATOR = new Creator<PageArgs>() {
        @Override
        public PageArgs createFromParcel(Parcel in) {
            return new PageArgs(in);
        }

        @Override
        public PageArgs[] newArray(int size) {
            return new PageArgs[size];
        }
    };

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
    }

    @Override
    public String toString() {
        return "PageArgs{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
