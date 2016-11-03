package beer.dku.com.beerprototype.page;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

import beer.dku.com.beerprototype.R;
import beer.dku.com.beerprototype.asynctasks.ImageSearchTask;

public class SearchImagePage extends Fragment
    implements View.OnClickListener {

    public final int REQ_CODE_SELECT_IMAGE = 200;
    public final int TACK_CAMERA = 100;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final String ARG_POSITION = "position";

    private String mParam1;
    private String mParam2;

    private int position;
    private AppCompatActivity mContext;
    private ImageView imageView;
    private ImageButton searchBtn;
    private ImageButton camaraBtn;
    private Uri imageURI;

    private ProgressDialog mDlg;

    public SearchImagePage() {
        // Required empty public constructor
    }

    public static SearchImagePage newInstance(String param1, String param2, int position) {
        SearchImagePage fragment = new SearchImagePage();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_search_image, container, false);

        imageView = (ImageView) view.findViewById(R.id.imageView_search);
        imageView.setOnClickListener(this);
        searchBtn = (ImageButton) view.findViewById(R.id.imgSearchBtn);
        searchBtn.setOnClickListener(this);
        searchBtn.setEnabled(false);
        camaraBtn = (ImageButton) view.findViewById(R.id.cameraBtn);
        camaraBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        mContext = (AppCompatActivity) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

        if(imageView.equals(v)) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
        } else if(camaraBtn.equals(v)) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, TACK_CAMERA);
        } else if(searchBtn.equals(v)) {
            mDlg = new ProgressDialog(mContext);
            mDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mDlg.setMessage("로그인 중 입니다.");
            mDlg.show();

            Log.d("beer1111",getRealImagePath(imageURI));
            String imgName = getRealImagePath(imageURI).split("/")[6];
            Log.d("beer1111",imgName);
            ImageSearchTask task = new ImageSearchTask(handler, getRealImagePath(imageURI), imgName);
            task.start();

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQ_CODE_SELECT_IMAGE) {
            if(resultCode == Activity.RESULT_OK) {

                try {
                    imageURI = data.getData();
                    Bitmap image = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), data.getData());
                    imageView.setImageBitmap(image);
                    searchBtn.setEnabled(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        } else if (requestCode == TACK_CAMERA) {
            if(resultCode == Activity.RESULT_OK) {
                imageURI = data.getData();
                imageView.setImageURI(imageURI);
                searchBtn.setEnabled(true);
            }
        }
    }

    private String getRealImagePath(Uri uri) {
        String [] proj = {MediaStore.Images.Media.DATA };
        Cursor cursor = mContext.managedQuery(uri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        String imgPath = cursor.getString(column_index);
        return imgPath;
    }

    final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String result = bundle.getString("result");
            mDlg.dismiss();
            if (result != null) {
                Toast.makeText(mContext, "이미지를 찾았습니다. " + result, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "이미지를 못 찾았습니다.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
