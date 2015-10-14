package connect.qaagility.com.myclass;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macpro on 8/17/15.
 */
public class GalleryData implements Parcelable {
    int eventId;
    String eventTitle;
    int totalImages;
    String primaryUrl,secondaryUrl,terniaryUrl;
    List<String> arr = new ArrayList<String>();

    public GalleryData(){

    }


    protected GalleryData(Parcel in) {
        eventId = in.readInt();
        eventTitle = in.readString();
        totalImages = in.readInt();
        primaryUrl = in.readString();
        secondaryUrl = in.readString();
        terniaryUrl = in.readString();
        arr = in.createStringArrayList();
    }

    public static final Creator<GalleryData> CREATOR = new Creator<GalleryData>() {
        @Override
        public GalleryData createFromParcel(Parcel in) {
            return new GalleryData(in);
        }

        @Override
        public GalleryData[] newArray(int size) {
            return new GalleryData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(eventId);
        dest.writeString(eventTitle);
        dest.writeInt(totalImages);
        dest.writeString(primaryUrl);
        dest.writeString(secondaryUrl);
        dest.writeString(terniaryUrl);
        dest.writeStringList(arr);
    }
}
