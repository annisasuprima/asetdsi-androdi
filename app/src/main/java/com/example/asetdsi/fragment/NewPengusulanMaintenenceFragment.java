package com.example.asetdsi.fragment;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.asetdsi.Retrofit.PortalClient.API_BASE_URL;

import android.Manifest;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asetdsi.BuildConfig;
import com.example.asetdsi.Detail_Form_Pengusulan_Maintenence_Activity;
import com.example.asetdsi.FileUtils;
import com.example.asetdsi.FormPeminjamanBarangActivity;
import com.example.asetdsi.R;
import com.example.asetdsi.Retrofit.PortalClient;
import com.example.asetdsi.adapter.ArrayMtAdapter;
import com.example.asetdsi.adapter.ListBarangMaintenenceAdapter;
import com.example.asetdsi.adapter.ListPickPhotoMtAdapter;
import com.example.asetdsi.adapter.RvArrayPhotoAdapter;
import com.example.asetdsi.model.ArrayMt;
import com.example.asetdsi.model.ArrayPhotoMt;
import com.example.asetdsi.model.DaftarBarangMaintenenceItem;
import com.example.asetdsi.model.DaftarBarangMaintenenceResponse;
import com.example.asetdsi.model.DetailFormPengusulanMaintenence;
import com.example.asetdsi.model.ListBarangMaintenence;
import com.example.asetdsi.model.Peminjaman;
import com.example.asetdsi.model.PengusulanBarang;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewPengusulanMaintenenceFragment extends Fragment {

    String masuk;
    Uri uri;
    String path;
    Bitmap bitmap;
    StringBuffer ab=null;
    View v;
    RecyclerView rvBarangMaintenencemtmt;
    RecyclerView rVlistPhotoMtmt;
    RecyclerView rVarrayListMt;
    ImageView pick_photomt;
    TextInputEditText deskripsi_permasalahanmt;
    ActionBar actionBar;
    RecyclerView rvListMtmt;
    Button BtnTambahLagiBrgMt;
    Button BtnTambahNextDetailMt;
    Button BtnFotomtmt;
    TextView nama_barang_arraymt,kondisi_arraymt,permasalahan_arraymt,kode_arraymt;

    private static final int Read_Permission = 101;
    private static final int PICK_IMAGE = 1;

//    private static final int MY_PERMISSION_REQUEST = 100;
//    private int PICK_IMAGE_FROM_GALLERY_REQUEST =1;
    public NewPengusulanMaintenenceFragment() {
        // Required empty public constructor
    }

//    Uri uri;
    String fileUri;
    String type = "0";
    ArrayList<DetailFormPengusulanMaintenence> listData = new ArrayList<DetailFormPengusulanMaintenence>();
    //ListCheckboxBarangMt
    ArrayList<ListBarangMaintenence> listBarangMaintenence = new ArrayList<>();

    //ListGambarPickMultiple
//    ArrayList<Uri> uri = new ArrayList<>();
//    ListPickPhotoMtAdapter listPickPhotoMtAdapter;

    //ListArrayPhto
    ArrayList<Uri> listuri = new ArrayList<>();
    RvArrayPhotoAdapter rvArrayPhotoAdapter;

    //ListDetailFormMt
    ArrayList<ArrayMt> listArray;
    ArrayMtAdapter arrayMtAdapter;


    public static NewPengusulanMaintenenceFragment getInstance(){
        NewPengusulanMaintenenceFragment newPengusulanMaintenenceFragment = new NewPengusulanMaintenenceFragment();
        return newPengusulanMaintenenceFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_new_pengusulan_maintenence, container, false);
        SharedPreferences preferences = getContext().getSharedPreferences(
                "com.example.asetdsi.PREFS",
                MODE_PRIVATE
        );

        Intent pengusulanIntent = getActivity().getIntent();
        Integer id  = pengusulanIntent.getIntExtra("id",0);

        TextView deskripsi_permasalahanmt = (TextInputEditText)v.findViewById(R.id.deskripsi_permasalahanmt);
//        ImageView pick_photomt = (ImageView)v.findViewById(R.id.pick_photomt);

        Button BtnFotomtmt = (Button)v.findViewById(R.id.BtnFotomtmt);
//        rVlistPhotoMtmt =(RecyclerView)v.findViewById(R.id.rVlistPhotoMtmt);

        ListBarangMaintenenceAdapter adapter = new ListBarangMaintenenceAdapter();
//        adapter.setListData(getListMaintenence());

        rvBarangMaintenencemtmt = (RecyclerView) v.findViewById(R.id.rvBarangMaintenencemtmt);
        rvBarangMaintenencemtmt.setAdapter(adapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rvBarangMaintenencemtmt.setLayoutManager(layoutManager);


        String accessToken = preferences.getString("ACCESS_TOKEN","");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;

        PortalClient client = retrofit.create(PortalClient.class);

        Call<DaftarBarangMaintenenceResponse> call = client.getBarangMt("Bearer "+accessToken,id);
        call.enqueue(new Callback<DaftarBarangMaintenenceResponse>() {
            @Override
            public void onResponse(Call<DaftarBarangMaintenenceResponse> call, Response<DaftarBarangMaintenenceResponse> response) {
                DaftarBarangMaintenenceResponse daftarBarangMaintenenceResponse = response.body();
                ArrayList<ListBarangMaintenence> listBarangMaintenence = new ArrayList<ListBarangMaintenence>();
                if(daftarBarangMaintenenceResponse != null){
                    List<DaftarBarangMaintenenceItem> daftarBarangMaintenenceItem = daftarBarangMaintenenceResponse.getData();
//                    Log.d("oi54", String.valueOf(listPeminjamanBarangItem.size()));
                    for(DaftarBarangMaintenenceItem pengusulanBarangItem : daftarBarangMaintenenceItem){
                        ListBarangMaintenence barangMaintenence = new ListBarangMaintenence(
                                pengusulanBarangItem.getAssetName(),
                                pengusulanBarangItem.getInventoryBrand(),
                                pengusulanBarangItem.getCondition(),
                                pengusulanBarangItem.getPhoto(),
                                pengusulanBarangItem.getItemCode(),
                                pengusulanBarangItem.getId()



                        );


                        listBarangMaintenence.add(barangMaintenence);
                    }
                }
                else{
                    Toast.makeText(getContext(),"Gagal Server",Toast.LENGTH_SHORT).show();


                }
                adapter.setListData(listBarangMaintenence);

            }

            @Override
            public void onFailure(Call<DaftarBarangMaintenenceResponse> call, Throwable t) {
                Toast.makeText(getContext(),"Gagal Menghubungi Server",Toast.LENGTH_SHORT).show();


            }
        });

        //Photo
//        listPickPhotoMtAdapter = new ListPickPhotoMtAdapter(uri);
//        rVlistPhotoMtmt.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//        rVlistPhotoMtmt.setAdapter(listPickPhotoMtAdapter) ;


        BtnFotomtmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(
                        Intent.createChooser(intent, "Select Image"),
                        PICK_IMAGE);
            }



                //PickPhotoMultiple
//                if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
//                        != PackageManager.PERMISSION_GRANTED
//                ){
//                    ActivityCompat.requestPermissions(getActivity(),
//                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},Read_Permission);
//
//                    return;
//                }
//
//                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2){
//                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
//                }
////                 intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE);
//            }
            //SampaiSini

        });

        //ArrayMt

        rvListMtmt = v.findViewById(R.id.rVarrayListMt);
        listArray=new ArrayList<>();

        arrayMtAdapter = new ArrayMtAdapter(this,listArray);
        rvListMtmt.setHasFixedSize(true);
        rvListMtmt.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rvListMtmt.setAdapter(arrayMtAdapter);


        BtnTambahLagiBrgMt= v.findViewById(R.id.BtnTambahLagiBrgMt);
        BtnTambahLagiBrgMt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<ArrayPhotoMt> arrayPhotoMts = new ArrayList<ArrayPhotoMt>();
                ArrayMt arrayMt = new ArrayMt();
                arrayMt.setPermasalahan_arraymt(deskripsi_permasalahanmt.getText().toString());
//                arrayMt.setPhoto_pengusulanmt(pick_photomt.setImageURI(uri));


                ab=new StringBuffer();
                boolean result = true;

                for (ListBarangMaintenence listBarangMaintenence : adapter.checkedlistData)
                {
                    SharedPreferences preferences =
                            getContext().getSharedPreferences("com.example.asetdsi.PREFS", MODE_PRIVATE);
//
//                    SharedPreferences.Editor mt = preferences.edit();
//                    mt.putString("merk_barang_mt", String.valueOf(listBarangMaintenence.getMerk_brg_maintenence()));
//                    mt.putString("kondisi_barang_mt",String.valueOf(listBarangMaintenence.getKondisi_brg_maintenence()));
//                    mt.putString("kode_barang_mt",String.valueOf(listBarangMaintenence.getItem_code()));
//                    mt.putString("inventory_item_id",String.valueOf(listBarangMaintenence.id));
//                    mt.apply();

                    ab.append(listBarangMaintenence.nama_brg_maintenence);
                    ab.append("\n");
                    ab.append(listBarangMaintenence.kondisi_brg_maintenence);
                    ab.append("\n");
                    ab.append(listBarangMaintenence.id);
                    ab.append("\n");

                    if(adapter.checkedlistData.size()>0){


                        arrayMt.setNama_barang_arraymt(listBarangMaintenence.merk_brg_maintenence);
                        arrayMt.setKode_arraymt(listBarangMaintenence.item_code);
                        arrayMt.setKondisi_arraymt(listBarangMaintenence.kondisi_brg_maintenence);

                        Log.d("y43", arrayMt.nama_barang_arraymt);

                        Toast.makeText(getContext(),ab.toString(),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(),"Please Check An Item First",Toast.LENGTH_SHORT).show();
                    }


                }

                listArray.add(arrayMt);
                arrayMtAdapter.notifyDataSetChanged();

            }

        });

        BtnTambahNextDetailMt = v.findViewById(R.id.BtnTambahNextDetailMtt);
        BtnTambahNextDetailMt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Detail_Form_Pengusulan_Maintenence_Activity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("list",listArray);
//                    bundle.putSerializable("pic_id",pengusulanBarang.pic_id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return v;



    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==PICK_IMAGE && resultCode==RESULT_OK)
//        {
//            Uri uri = data.getData();
//            ImageView pick_photomt = v.findViewById(R.id.pick_photomt);
//            pick_photomt.setImageURI(uri);
//        }
//
//
//
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null){

            uri = data.getData();
            fileUri = uri.toString();
            path = FileUtils.getRealPath(getContext(),uri);
            File file = new File(path);
            String strFileName = file.getName();
            BtnFotomtmt = v.findViewById(R.id.BtnFotomtmt);
            BtnFotomtmt.setText(strFileName);
            ImageView pick_photomt = v.findViewById(R.id.pick_photomt);
            pick_photomt.setImageURI(uri);
            Toast.makeText(getContext(),path.toString(),Toast.LENGTH_LONG).show();
        }
    }


//    public void uploadFile(Uri uri){
//        final Button name = v.findViewById(R.id.BtnTambahLagiBrgMt);
//
//        SharedPreferences preferences = this.getContext().getSharedPreferences(
//                "com.example.asetdsi.PREFS",
//                Context.MODE_PRIVATE
//        );
//        String accesstoken = preferences.getString("ACCESS_TOKEN","");
//
//        RequestBody descriptionPart = RequestBody.create(MultipartBody.FORM, name.getText().toString());
//
//        File originalfile = new File(FileUtils.getRealPath(this, uri));
//
//        RequestBody filePart = RequestBody.create(
//                okhttp3.MediaType.parse("image/*"),
//                originalfile
//        );
//
//        MultipartBody.Part file = MultipartBody.Part.createFormData("image", originalfile.getName(),filePart);
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                ;
//
//        PortalClient client = retrofit.create(PortalClient.class);
//
//        deskripsi_permasalahanmt = v.findViewById(R.id.deskripsi_permasalahanmt)
//
//
////        Toast.makeText(this,file+title+desc+type+attendance_id,Toast.LENGTH_SHORT).show();
//        Call<ServerResponse> call = client.uploadFile("Bearer "+accesstoken, file, title, type, attendance_id, desc);
//
//        SweetAlertDialog pDialog = new SweetAlertDialog(AddLeaveActivity.this, SweetAlertDialog.PROGRESS_TYPE);
//        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        pDialog.setTitleText("Mengirim..");
//        pDialog.setCancelable(false);
//        pDialog.show();
//
//        call.enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                pDialog.dismiss();
//                try {
//                    if(response.body()!=null){
//                        new SweetAlertDialog(AddLeaveActivity.this, SweetAlertDialog.SUCCESS_TYPE)
//                                .setTitleText("Berhasil!")
//                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
//                                    @Override
//                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                        Intent mainIntent = new Intent(AddLeaveActivity.this, MainActivity.class);
//                                        startActivity(mainIntent);
//                                        finish();
//                                    }
//                                })
//                                .show();
////                        Toast.makeText(AddLeaveActivity.this,response.body().getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                    if(response.errorBody()!=null) {
//                        new SweetAlertDialog(AddLeaveActivity.this, SweetAlertDialog.ERROR_TYPE)
//                                .setTitleText("Hanya Bisa Kirim Satu Kali Permohonan")
//                                .show();
//                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//            }
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                pDialog.dismiss();
//                new SweetAlertDialog(AddLeaveActivity.this, SweetAlertDialog.ERROR_TYPE)
//                        .setTitleText("Koneksi Gagal")
//                        .show();
////                Toast.makeText(AddLeaveActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


//PickMultipleImage
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && null != data) {
//            if (data.getClipData() != null) {
//                int countOfImages = data.getClipData().getItemCount();
//                for (int i = 0; i < countOfImages; i++) {
//                    Uri imageuri = data.getClipData().getItemAt(i).getUri();
//                    uri.add(imageuri);
//                }
//            } else {
//                Uri imageuri = data.getData();
//                uri.add(imageuri);
////                listuri.add(imageuri);
//            }
//            listPickPhotoMtAdapter.notifyDataSetChanged();
////            rvArrayPhotoAdapter.notifyDataSetChanged();
//
//        } else {
//            Toast.makeText(getContext(), "You haven't pick any image", Toast.LENGTH_SHORT).show();
//        }
    }


