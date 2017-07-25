package com.hejunlin.liveplayback;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hejunlin.liveplayback.adapter.MovIeTypeAdapter;
import com.hejunlin.liveplayback.adapter.MoviesAdapter;
import com.hejunlin.liveplayback.contacts.Contacts;
import com.hejunlin.liveplayback.entity.Movie;
import com.hejunlin.liveplayback.layout.TvGridLayoutManager;
import com.hejunlin.liveplayback.playfile.FileUtil;
import com.hejunlin.liveplayback.service.PlayFileService;
import com.hejunlin.liveplayback.utils.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static com.hejunlin.liveplayback.utils.CreateQRCode.getMacAddressAndCreateQRCode;
import static com.hejunlin.liveplayback.utils.LoadMovies.filterLocalMovies;
import static com.hejunlin.liveplayback.utils.LoadMovies.filterNetWorkMovies;
import static com.hejunlin.liveplayback.utils.LoadMovies.getLocalMovies;
import static com.hejunlin.liveplayback.utils.LoadMovies.getNetWorkMovie;
import static com.hejunlin.liveplayback.utils.MoviesUtils.createMovies;


public class MovieHomeActivity extends BaseActivity {

    private RecyclerView mMovieRecyclerView, mMovieTypeRecyclerView;
    private MoviesAdapter mMoviesAdapter;
    private MovIeTypeAdapter mMovieTypeRecyclerViewAdapter;
    private ProgressDialog mLoadingAllMovieDialog = null;
    private List<Movie> mNetWorkMovies, mLocalMovies, mPopularMovies, mScienceMovies,
            mRomanticMovies, mCostumeMovies, mActiovMovies;
    private LoadMoviesTask mLoadNetWorkMoviesTask = null;
    private TextView mMovieHomeTopMovieType, mMovieHomeTopCunrrentMovieNo, mNoMovies;
    private ImageView mQRCrcode;
    private int mCurrentMovieType, mCurrentMoviesSize, mCurrentMovieItemPosition;
    private int currentType = 0, lastItemPosition = 0, firstItemPosition = 0;
    private String[] mListMovieType = {"热门精选", "科幻大片", "爱情喜剧", "古装武侠", "冒险动作", "恐怖悬疑", "战争纪实", "文艺歌舞", "历史传记"};
    private List[] mAllTypeMovies = {mNetWorkMovies, mLocalMovies, mPopularMovies, mScienceMovies,
            mRomanticMovies, mCostumeMovies, mActiovMovies, mCostumeMovies, mActiovMovies};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moviehome_mainpage);
//        startPlayFileService();
        getViews();
        init();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(this, PlayFileService.class);
        stopService(intent);
    }

    /*
    * 开启service
    * */
    private void startPlayFileService() {
        Intent intent = new Intent(this, PlayFileService.class);
        startService(intent);
    }

    public void getViews() {
        mMovieTypeRecyclerView = (RecyclerView) findViewById(R.id.moviehome_movie_type);
        mMovieRecyclerView = (RecyclerView) findViewById(R.id.moviehome_allmovie);
        mMovieHomeTopMovieType = (TextView) findViewById(R.id.moviehome_title_movietype);
        mMovieHomeTopCunrrentMovieNo = (TextView) findViewById(R.id.moviehome_title_current_movienum);
        mQRCrcode = (ImageView) findViewById(R.id.qrccode);
        mNoMovies = (TextView) findViewById(R.id.tv_noMovie);
    }

    public void init() {
        //获得IP地址
        getIpAddress();

        //设置二维码
        setQRCode();

        //初始化加载电影时的dialog
        initLoadingMoviesDialog();

        //初始化不同类型的电影
//        loadMoviesByTypes();

        //设置电影分类的布局管理器
        initRecycle(mMovieTypeRecyclerView, true);

        //设置电影展示的布局管理器
        initRecycle(mMovieRecyclerView, false);

        //设置电影分类的adapter以及select事件
        setMovieTypeAdapter();
    }

    private void getIpAddress() {
        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        Log.i("hm", "ip=" + hostIp);
        Toast.toast(MovieHomeActivity.this, "IP=" + hostIp);
        FileUtil.ip = hostIp;
        FileUtil.port = 2222;
    }

    /**
     * 设置带mac地址的二维码
     */
    private void setQRCode() {
        Bitmap qrcode = getMacAddressAndCreateQRCode(174, 174);
        mQRCrcode.setImageBitmap(qrcode);
    }

    /*
    * 加载不同类型的影视
    * */
    private void loadMoviesByTypes() {
        loadLocalMovies();
        loadNetWorkMovies();
        mPopularMovies = createMovies(Contacts.POPULAR_MOVIES_IMG, Contacts.POPULAR_MOVIES_NAME);
        mScienceMovies = createMovies(Contacts.SCIENCE_MOVIES_IMG, Contacts.SCIENCE_MOVIES_NAME);
        mRomanticMovies = createMovies(Contacts.ROMANTIC_MOVIES_IMG, Contacts.ROMANTIC_MOVIES_NAME);
        mCostumeMovies = createMovies(Contacts.COSTUME_MOVIES_IMG, Contacts.COSTUME_MOVIES_NAME);
        mActiovMovies = createMovies(Contacts.ACTION_MOVIES_IMG, Contacts.ACTION_MOVIES_NAME);
    }

    /**
     * 加载网络存储服务器上的资源
     */
    private void loadNetWorkMovies() {
        if (mLoadNetWorkMoviesTask == null || mLoadNetWorkMoviesTask.getStatus().equals(AsyncTask.Status.FINISHED)) {
            mLoadNetWorkMoviesTask = new LoadMoviesTask();
            mLoadNetWorkMoviesTask.execute();
        }
    }

    /**
     * 加载本地资源
     */
    private void loadLocalMovies() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    List<Movie> movies = getLocalMovies(MovieHomeActivity.this);
//                    Log.i("hm", "movies.size=" + movies.size());
                    mLocalMovies = new ArrayList<>();
                    mLocalMovies = getLocalMovies(mLocalMovies, Environment.getExternalStorageDirectory());
//                    mLocalMovies = getLocalMovies(MovieHomeActivity.this);
                    Log.i("hm", "size=" + mLocalMovies.size());
                    mLocalMovies = filterLocalMovies(mLocalMovies);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 初始化布局管理器
     *
     * @param mRecyclerView
     * @param isType
     */
    private void initRecycle(RecyclerView mRecyclerView, boolean isType) {
        //设置布局管理器
        if (isType) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
        } else {
            RecyclerView.LayoutManager tvGridLayoutManager = new TvGridLayoutManager(this, 5);
            mRecyclerView.setLayoutManager(tvGridLayoutManager);
        }
        mRecyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    if (((RecyclerView) view).getChildCount() > 0) {
                        ((RecyclerView) view).getChildAt(0).requestFocus();
                    }
                }
            }
        });
    }

    /**
     * 初始化加载电影时的dialog
     */
    private void initLoadingMoviesDialog() {
        mLoadingAllMovieDialog = new ProgressDialog(this);
        mLoadingAllMovieDialog.setMessage("正在为您努力加载...");
        mLoadingAllMovieDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 设置电影分类recyclerview的item select事件
     */
    private void setMovieTypeAdapter() {
        mMovieTypeRecyclerViewAdapter = new MovIeTypeAdapter(this, mListMovieType);
        mMovieTypeRecyclerViewAdapter.setOnItemSelectListener(new MovIeTypeAdapter.OnRecyclerViewItemSelectListener() {
            @Override
            public void onItemSelect(View view, int position) {
                ShowOrHideArrow(position);
//            loadAllMovieInType(mListMovieType[position]);
                /**
                 * 消息发送应当在loadAllMovieInType中，当接收到正确的查询结果并解析完成赋值给movielist后进行adapter绑定显示。
                 */
                Message message = new Message();
                message.what = 1;
                message.arg1 = position;
                myHandler.sendMessage(message);
            }
        });
        mMovieTypeRecyclerView.setAdapter(mMovieTypeRecyclerViewAdapter);
    }

    /**
     * 显示或隐藏向上向下箭头
     *
     * @param position
     */
    void ShowOrHideArrow(int position) {
        ImageView up = (ImageView) findViewById(R.id.moviehome_listview_up);
        ImageView down = (ImageView) findViewById(R.id.moviehome_listview_down);
        if (position > 5) {
            up.setVisibility(View.VISIBLE);
        } else if (position == 0) {
            up.setVisibility(View.INVISIBLE);
        }
        if (position == mListMovieType.length - 1) {
            down.setVisibility(View.INVISIBLE);
        } else if (position < 3) {
            down.setVisibility(View.VISIBLE);
        }
    }

    @SuppressLint("HandlerLeak")
    Handler myHandler = new Handler() {
        @SuppressLint("HandlerLeak")
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int position = msg.arg1;
                    mCurrentMovieType = position;
                    if (position == 0) {
                        //网络存储服务器上的电影
                        initRecycerView(mLocalMovies);
                    } else if (position == 1) {
                        //热门精选的电影
                        initRecycerView(mNetWorkMovies);
                    } else if (position == 2) {
                        //科幻大片
                        initRecycerView(mScienceMovies);
                    } else if (position == 3) {
                        //爱情喜剧
                        initRecycerView(mRomanticMovies);
                    } else if (position == 4) {
                        //古装武侠
                        initRecycerView(mCostumeMovies);
                    } else if (position == 5) {
                        //冒险动作
                        initRecycerView(mActiovMovies);
                    } else if (position == 6) {
                        //本地
                        initRecycerView(mScienceMovies);
                    } else if (position == 7) {
                        //恐怖悬疑
                        initRecycerView(mPopularMovies);
                    } else if (position == 8) {
                        //本地
//                        initRecycerView(mScienceMovies);
                        initRecycerView(null);
                    } else {
                        mMovieRecyclerView.setAdapter(null);
                        mLoadingAllMovieDialog.show();
//                        Toast toast = Toast.makeText(getApplicationContext(),
//                                "加载失败，请稍后再试", Toast.LENGTH_LONG);
//                        toast.setGravity(Gravity.FILL_HORIZONTAL, 0, 0);
//                        toast.show();
                    }
                    mMovieHomeTopMovieType.setText(mListMovieType[position]);

                    break;

                default:
                    break;
            }

        }
    };


    /**
     * 绑定电影adapter
     *
     * @param movieInfos 电影集合
     */
    public void initRecycerView(final List<Movie> movieInfos) {


        if (mMovieRecyclerView.getAdapter() != null) {
            mMovieRecyclerView.setAdapter(null);
        }
        if (movieInfos != null) {
            mMovieRecyclerView.setVisibility(View.VISIBLE);
            mNoMovies.setVisibility(View.GONE);
            mMoviesAdapter = new MoviesAdapter(this, movieInfos);
            mMoviesAdapter.setOnItemSelectListener(new MoviesAdapter.OnRecyclerViewItemSelectListener() {
                @Override
                public void onItemSelect(View view, int position) {
                    //设置当前item以及总数
                    mMovieHomeTopCunrrentMovieNo.setText(position + 1 + "/" + movieInfos.size());
                    mCurrentMoviesSize = movieInfos.size();
                    mCurrentMovieItemPosition = position;
                }
            });

            mMoviesAdapter.setOnItemClickListener(new MoviesAdapter.OnRecyclerViewItemClickListener() {
                @Override
                public void onItemClick(View view, Movie movie) {
//                    http://10.0.4.15:2222/smb=smb://kupaworld:kupa1806@192.168.0.195/video/生化危机：终章.mp4
//                    http:127.0.0.1:0  /smb=smb://kupaworld:kupa1806@192.168.0.195/video/星球大战外传侠盗一号.mkv

                    Intent intent = new Intent(MovieHomeActivity.this, MovieDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("fileitem", movie);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            mMovieRecyclerView.setAdapter(mMoviesAdapter);
        } else {
            mMovieRecyclerView.setVisibility(View.GONE);
            mNoMovies.setVisibility(View.VISIBLE);
        }
    }


    /**
     * 加载当前类型下的网络电影
     *
     * @param movieType
     */
    void loadAllMovieInType(String movieType) {
        RequestParams searchAllMovieRequest = new RequestParams(Contacts.SEARCH_ALLMOVIE_BYTYPE);
        searchAllMovieRequest.addBodyParameter("type", String.valueOf(movieType));
        x.http().post(searchAllMovieRequest, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    resolveResult(result);
                } catch (JSONException e) {
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                return false;
            }
        });

    }

    /**
     * 接收服务端返回的结果
     *
     * @param message
     * @throws JSONException
     */
    void resolveResult(String message) throws JSONException {

        Log.i("result", message);

        JSONTokener jsonTokener = new JSONTokener(message);
        JSONObject jsonObject = (JSONObject) jsonTokener.nextValue();
//        if (jsonObject.optBoolean("result")) {
//        }
    }

    /**
     * 加载电影
     * Params, Progress, Result
     */
    class LoadMoviesTask extends AsyncTask<String, Void, List<Movie>> {
        private List<Movie> movieInfos = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingAllMovieDialog.show();
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            movieInfos = getNetWorkMovie(movieInfos);
            movieInfos = filterNetWorkMovies(movieInfos);
            mNetWorkMovies = movieInfos;
            return movieInfos;
        }

        @Override
        protected void onPostExecute(List<Movie> result) {
            super.onPostExecute(result);
            mLoadingAllMovieDialog.dismiss();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_UP://向上
                break;
            case KeyEvent.KEYCODE_DPAD_DOWN://向下
//                Boolean isEndLine = isEndLine(mMovieRecyclerView, mCurrentMovieItemPosition);
//                if (isEndLine && mMovieRecyclerView.hasFocus() && mMovieRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE && getMovieTypeCurrentType() + 1 < mAllTypeMovies.length) {
//                    Message message = new Message();
//                    message.what = 1;
//                    message.arg1 = getMovieTypeCurrentType() + 1;
//                    myHandler.sendMessage(message);
//                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT://向左
                if (mCurrentMovieItemPosition % 5 == 0 && mMovieRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE && mMovieRecyclerView.hasFocus()) {
                    mMovieTypeRecyclerView.getChildAt(getMovieTypeCurrentType()).requestFocus();
                }
                break;
            case KeyEvent.KEYCODE_DPAD_RIGHT://向右
                if (mMovieTypeRecyclerView.hasFocus() && mMovieTypeRecyclerView.getScrollState() == RecyclerView.SCROLL_STATE_IDLE && mMovieRecyclerView.getChildCount() != 0) {
//                    int i = ((GridLayoutManager) mMovieRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                    mMovieRecyclerView.getChildAt(0).requestFocus();
                }
                break;
            case KeyEvent.KEYCODE_ENTER://确定
                break;
            case KeyEvent.KEYCODE_BACK://返回
                if (event.getRepeatCount() == 0) {
                    finish();
                }
                break;
            case KeyEvent.KEYCODE_HOME://主页
                break;
            case KeyEvent.KEYCODE_MENU://菜单
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    private int getMovieTypeCurrentType() {
        RecyclerView.LayoutManager layoutManager = mMovieTypeRecyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            lastItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            firstItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            currentType = mCurrentMovieType - firstItemPosition;
            return currentType;
        } else {
            return 0;
        }
    }

    public static Boolean isEndLine(RecyclerView recyclerView, int currentItemPosition) {
        int countLine, moviesCount = recyclerView.getChildCount();
        if (moviesCount % 5 != 0) {
            countLine = (moviesCount / 5) + 1;
        } else {
            countLine = (moviesCount / 5);
        }
        if (currentItemPosition > (5 * (countLine - 1)) && recyclerView.getScrollState() == recyclerView.SCROLL_STATE_IDLE) {
            return true;
        } else {
            return false;
        }
    }
}
