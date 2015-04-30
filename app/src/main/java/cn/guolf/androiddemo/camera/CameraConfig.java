package cn.guolf.androiddemo.camera;

import org.json.JSONArray;

/**
 * Created by glf on 2015/4/30.
 */
public class CameraConfig {
    private int quality;
    private int destType;
    private int srcType;
    private int targetWidth;
    private int targetHeight;
    private int encodingType;
    private int mediaType;
    private boolean allowEdit;
    private boolean correctOrientation;
    private boolean saveToPhotoAlbum;

    enum EncodingType {
        JPEG, PNG
    }

    enum MediaType {
        PICTURE, VIDEO, ALLMEDIA
    }

    enum SrcType {
        PHOTOLIBRARY, CAMERA, SAVEDPHOTOALBUM
    }

    public CameraConfig() {
        this.quality = 80;
        this.destType = 1;  //0 return base64,1 retur file url
        this.srcType = SrcType.CAMERA.ordinal();
        this.targetWidth = 500;
        this.targetHeight = 600;
        this.encodingType = EncodingType.JPEG.ordinal();
        this.mediaType = MediaType.PICTURE.ordinal();
        this.allowEdit = false;
        this.correctOrientation = false;
        this.saveToPhotoAlbum = false;
    }

    public JSONArray getConfig() {
        JSONArray json = new JSONArray();
        json.put(this.quality);
        json.put(this.destType);
        json.put(this.srcType);
        json.put(this.targetWidth);
        json.put(this.targetHeight);
        json.put(this.encodingType);
        json.put(this.mediaType);
        json.put(this.allowEdit);
        json.put(this.correctOrientation);
        json.put(this.saveToPhotoAlbum);
        return json;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public int getDestType() {
        return destType;
    }

    public void setDestType(int destType) {
        this.destType = destType;
    }

    public int getSrcType() {
        return srcType;
    }

    public void setSrcType(int srcType) {
        this.srcType = srcType;
    }

    public int getTargetWidth() {
        return targetWidth;
    }

    public void setTargetWidth(int targetWidth) {
        this.targetWidth = targetWidth;
    }

    public int getTargetHeight() {
        return targetHeight;
    }

    public void setTargetHeight(int targetHeight) {
        this.targetHeight = targetHeight;
    }

    public int getEncodingType() {
        return encodingType;
    }

    public void setEncodingType(int encodingType) {
        this.encodingType = encodingType;
    }

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }

    public boolean isAllowEdit() {
        return allowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        this.allowEdit = allowEdit;
    }

    public boolean isCorrectOrientation() {
        return correctOrientation;
    }

    public void setCorrectOrientation(boolean correctOrientation) {
        this.correctOrientation = correctOrientation;
    }

    public boolean isSaveToPhotoAlbum() {
        return saveToPhotoAlbum;
    }

    public void setSaveToPhotoAlbum(boolean saveToPhotoAlbum) {
        this.saveToPhotoAlbum = saveToPhotoAlbum;
    }
}
