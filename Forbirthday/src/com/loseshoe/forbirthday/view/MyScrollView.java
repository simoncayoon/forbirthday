package com.loseshoe.forbirthday.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.loseshoe.forbirthday.util.ImageLoader;

public class MyScrollView extends ScrollView implements OnTouchListener{

	/** 
     * 每页要加载的图片数量 
     */  
    public static final int PAGE_SIZE = 15;  
  
    /** 
     * 记录当前已加载到第几页 
     */  
    private int page;  
  
    /** 
     * 每一列的宽度 
     */  
//    private int columnWidth;  
  
    /** 
     * 当前第一列的高度 
     */  
//    private int firstColumnHeight;  
  
    /** 
     * 当前第二列的高度 
     */  
//    private int secondColumnHeight;  
  
    /** 
     * 当前第三列的高度 
     */  
//    private int thirdColumnHeight;  
  
    /** 
     * 是否已加载过一次layout，这里onLayout中的初始化只需加载一次 
     */  
    private boolean loadOnce;  
  
    /** 
     * 对图片进行管理的工具类 
     */  
    private ImageLoader imageLoader;  
  
    /** 
     * 第一列的布局 
     */  
//    private LinearLayout firstColumn;  
  
    /** 
     * 第二列的布局 
     */  
//    private LinearLayout secondColumn;  
  
    /** 
     * 第三列的布局 
     */  
//    private LinearLayout thirdColumn;  
  
    /** 
     * 记录所有正在下载或等待下载的任务。 
     */  
//    private static Set<LoadImageTask> taskCollection;  
  
    /** 
     * MyScrollView下的直接子布局。 
     */  
//    private static View scrollLayout;  
  
    /** 
     * MyScrollView布局的高度。 
     */  
//    private static int scrollViewHeight;  
  
    /** 
     * 记录上垂直方向的滚动距离。 
     */  
//    private static int lastScrollY = -1;  
  
    /** 
     * 记录所有界面上的图片，用以可以随时控制对图片的释放。 
     */  
//    private List<ImageView> imageViewList = new ArrayList<ImageView>();  
  
    /** 
     * 在Handler中进行图片可见性检查的判断，以及加载更多图片的操作。 
     */  
    private static Handler handler = new Handler() {  
  
        public void handleMessage(android.os.Message msg) {  
//            MyScrollView myScrollView = (MyScrollView) msg.obj;  
//            int scrollY = myScrollView.getScrollY();  
            // 如果当前的滚动位置和上次相同，表示已停止滚动  
//            if (scrollY == lastScrollY) {  
//                // 当滚动的最底部，并且当前没有正在下载的任务时，开始加载下一页的图片  
//                if (scrollViewHeight + scrollY >= scrollLayout.getHeight()  
//                        && taskCollection.isEmpty()) {  
//                    myScrollView.loadMoreImages();  
//                }  
//                myScrollView.checkVisibility();  
//            } else {  
//                lastScrollY = scrollY;  
//                Message message = new Message();  
//                message.obj = myScrollView;  
//                // 5毫秒后再次对滚动位置进行判断  
//                handler.sendMessageDelayed(message, 5);  
//            }  
        };  
  
    };  
  
    /** 
     * MyScrollView的构造函数。 
     *  
     * @param context 
     * @param attrs 
     */  
    public MyScrollView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
        imageLoader = ImageLoader.getInstance();  
//        taskCollection = new HashSet<LoadImageTask>();  
        setOnTouchListener(this);  
    }  
  
    /** 
     * 进行一些关键性的初始化操作，获取MyScrollView的高度，以及得到第一列的宽度值。并在这里开始加载第一页的图片。 
     */  
    @Override  
    protected void onLayout(boolean changed, int l, int t, int r, int b) {  
        super.onLayout(changed, l, t, r, b);  
        if (changed && !loadOnce) {  
//            scrollViewHeight = getHeight();  
//            scrollLayout = getChildAt(0);  
//            firstColumn = (LinearLayout) findViewById(R.id.first_column);  
//            secondColumn = (LinearLayout) findViewById(R.id.second_column);  
//            thirdColumn = (LinearLayout) findViewById(R.id.third_column);  
//            columnWidth = firstColumn.getWidth();  
            loadOnce = true;  
            loadMoreImages();  
        }  
    }  
  
    /** 
     * 监听用户的触屏事件，如果用户手指离开屏幕则开始进行滚动检测。 
     */  
    @Override  
    public boolean onTouch(View v, MotionEvent event) {  
        if (event.getAction() == MotionEvent.ACTION_UP) {  
            Message message = new Message();  
            message.obj = this;  
            handler.sendMessageDelayed(message, 5);  
        }  
        return false;  
    }  
  
    /** 
     * 开始加载下一页的图片，每张图片都会开启一个异步线程去下载。 
     */  
    public void loadMoreImages() {  
//        if (hasSDCard()) {  
//            int startIndex = page * PAGE_SIZE;  
//            int endIndex = page * PAGE_SIZE + PAGE_SIZE;  
//            if (startIndex < Images.imageUrls.length) {  
//                Toast.makeText(getContext(), "正在加载...", Toast.LENGTH_SHORT)  
//                        .show();  
//                if (endIndex > Images.imageUrls.length) {  
//                    endIndex = Images.imageUrls.length;  
//                }  
//                for (int i = startIndex; i < endIndex; i++) {  
//                    LoadImageTask task = new LoadImageTask();  
//                    taskCollection.add(task);  
//                    task.execute(Images.imageUrls[i]);  
//                }  
//                page++;  
//            } else {  
//                Toast.makeText(getContext(), "已没有更多图片", Toast.LENGTH_SHORT)  
//                        .show();  
//            }  
//        } else {  
//            Toast.makeText(getContext(), "未发现SD卡", Toast.LENGTH_SHORT).show();  
//        }  
    }  
  
    /** 
     * 遍历imageViewList中的每张图片，对图片的可见性进行检查，如果图片已经离开屏幕可见范围，则将图片替换成一张空图。 
     */  
//    public void checkVisibility() {  
//        for (int i = 0; i < imageViewList.size(); i++) {  
//            ImageView imageView = imageViewList.get(i);  
//            int borderTop = (Integer) imageView.getTag(R.string.border_top);  
//            int borderBottom = (Integer) imageView  
//                    .getTag(R.string.border_bottom);  
//            if (borderBottom > getScrollY()  
//                    && borderTop < getScrollY() + scrollViewHeight) {  
//                String imageUrl = (String) imageView.getTag(R.string.image_url);  
//                Bitmap bitmap = imageLoader.getBitmapFromMemoryCache(imageUrl);  
//                if (bitmap != null) {  
//                    imageView.setImageBitmap(bitmap);  
//                } else {  
//                    LoadImageTask task = new LoadImageTask(imageView);  
//                    task.execute(imageUrl);  
//                }  
//            } else {  
//                imageView.setImageResource(R.drawable.empty_photo);  
//            }  
//        }  
//    }  
  
  
//
//	private boolean hasSDCard() {
//		// TODO Auto-generated method stub
//		return Environment.MEDIA_MOUNTED.equals(Environment  
//                .getExternalStorageState());  
//	}  
}
