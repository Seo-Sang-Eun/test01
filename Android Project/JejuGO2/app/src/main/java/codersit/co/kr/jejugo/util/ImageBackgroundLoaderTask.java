////URL을 BitMap으로 변환하는 클래스.
//package codersit.co.kr.jejugo.util;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.os.AsyncTask;
//import android.util.Log;
//import android.widget.ImageView;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//public class ImageBackgroundLoaderTask extends AsyncTask<Void, Void, Bitmap>
//{
//    /** The target image view to load an image */
//    private ImageView imageView;
//
//    private Context mContext;
//
//    /** The address where an image is stored. */
//    private String imageAddress;
//
//    public ImageBackgroundLoaderTask(Context context,ImageView imageView, String imageAddress) {
//        this.imageView = imageView;
//        this.imageAddress = imageAddress;
//        mContext = context;
//    }
//
//    @Override
//    protected Bitmap doInBackground(Void... params) {
//        Bitmap bitmap = null;
//        try {
//            InputStream is = new java.net.URL(this.imageAddress).openStream();
//            bitmap = BitmapFactory.decodeStream(is);
//        } catch (IOException e) {
//            Log.e("ImageLoaderTask", "Cannot load image from " + this.imageAddress);
//        }
//        return bitmap;
//    }
//
//    @Override
//    protected void onPostExecute(Bitmap bitmap) {
//
//        BitmapDrawable bdrawable = new BitmapDrawable(mContext.getResources(),bitmap);
//
//
//        imageView.setBackgroundDrawable( bdrawable);
////        this.imageView.setBackground(bitmap);
//    }
//}
