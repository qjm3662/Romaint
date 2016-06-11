package com.example.qjm3662.newproject.Tool;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.EditText;

import com.example.qjm3662.newproject.Data.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by qjm3662 on 2016/5/31 0031.
 */
public class Tool {
    //JsonObject to User
    public static void str_to_user(String s, String s2){
        if(s != null){
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject user_info = jsonObject.getJSONObject("user");
                User user = User.getInstance();
                user.setAvatar(user_info.getString(User.USER_AVATAR));
                user.setSign(user_info.getString(User.USER_SIGN));
                user.setUsername(user_info.getString(User.USER_USER_NAME));
                user.setSex(user_info.getInt(User.USER_SEX));
//                user.setCollectedStoriesCount(jsonObject.getInt(User.USER_COLLECTED_STORIES_COUNT));
//                System.out.println(user.getCollectedStoriesCount());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if(s2 != null){
            try {
                System.out.println(s2);
                JSONObject user_info = new JSONObject(s2);
                User user = User.getInstance();
                user.setId(user_info.getInt(User.USER_ID));
                user.setLoginToken(user_info.getString(User.USER_LOGIN_TOKEN));
                user.setToken(user_info.getString(User.USER_TOKEN));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }



    public static void insertPic(Bitmap bitmap, String path, Context context, EditText tv) {

        // 根据Bitmap对象创建ImageSpan对象
        ImageSpan imageSpan = new ImageSpan(context, bitmap);

        // 创建一个SpannableString对象，以便插入用ImageSpan对象封装的图像
        //System.out.println(Tool.getPath(this,uri));
        //<img alt="" src="1.jpg"></img>
        path = "<img" + path + "></img>";

        SpannableString spannableString = new SpannableString(path);

        // 用ImageSpan对象替换指定的字符串
        spannableString.setSpan(imageSpan, 0, path.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // 将选择的图片追加到EditText中光标所在位置
        int index = tv.getSelectionStart(); // 获取光标所在位置
        Editable edit_text = tv.getEditableText();
        if (index < 0 || index >= edit_text.length()) {
            edit_text.append('\n');
            edit_text.append(spannableString);
            edit_text.append('\n');
        } else {
            edit_text.insert(index, spannableString);
        }

        String s = tv.getText().toString();
        System.out.println(s.length());
    }


    public static Bitmap resize_bitmap(Bitmap b,float x,float y)
    {
        //b = compressImage(b);
        int w=b.getWidth();
        int h=b.getHeight();
        float sx=(float)x/w;//要强制转换，不转换我的在这总是死掉。
        float sy=(float)y/h;
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy); // 长和宽放大缩小的比例
        Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w,
                h, matrix, true);
        return resizeBmp;
    }

    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>100) {    //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


    /***
     * 图片的缩放方法
     *
     * @param bitmap
     *            ：源图片资源
     * @param newWidth
     *            ：缩放后宽度
     * @param newHeight
     *            ：缩放后高度
     * @return
     */
    public static Bitmap zoomImage(Bitmap bitmap, double newWidth,
                                   double newHeight) {

        bitmap = compressImage(bitmap);
        System.out.println("new" + newHeight + "  " + newWidth);
        // 获取这个图片的宽和高
        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap1;
    }






    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {

            // Return the remote address
            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri The Uri to query.
     * @param selection (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
