package tdc.edu.vn.qlsv.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import tdc.edu.vn.qlsv.Model.ThietBi;
import tdc.edu.vn.qlsv.R;


public class CustomAdapterTB extends RecyclerView.Adapter<CustomAdapterTB.MyViewHolder> {
    private int layoutID;
    private ArrayList<ThietBi> data;
    private Listener listener;
    public static interface Listener{
        public void onClick(int position);
    }

    public CustomAdapterTB(int layoutID, ArrayList<ThietBi> data) {
        this.layoutID = layoutID;
        this.data = data;

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView info_xuatXu;
        private TextView info_maTB;
        private TextView info_tenTB;
        private TextView info_maLoai;
        private  ImageView info_signature;
        private CardView cardView;

        public MyViewHolder(@NonNull CardView v) {
            super(v);
            info_xuatXu=v.findViewById(R.id.info_xuatXu);
            info_maTB=v.findViewById(R.id.info_maTB);
            info_tenTB=v.findViewById(R.id.info_tenTB);
            info_maLoai=v.findViewById(R.id.info_loaiTB);
            info_signature=v.findViewById(R.id.info_signature);
            cardView=v;
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        CardView cardView= (CardView) inflater.inflate(layoutID,viewGroup,false);
        return new MyViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        final CardView cardView=myViewHolder.cardView;
        ThietBi thietBi=data.get(i);
        if(thietBi.getXuatXuTB().equals("Việt Nam"))
            myViewHolder.info_xuatXu.setImageResource(R.drawable.vietnam);
        else if (thietBi.getXuatXuTB().equals("Mỹ"))
            myViewHolder.info_xuatXu.setImageResource(R.drawable.usa);
        else
            myViewHolder.info_xuatXu.setImageResource(R.drawable.korean);

        myViewHolder.info_maLoai.setText("Mã loại:"+thietBi.getMaLoaiTB());
        myViewHolder.info_tenTB.setText("Tên Thiết Bị:"+thietBi.getTenTB());
        myViewHolder.info_maTB.setText("Mã thiết bị:"+thietBi.getMaTB());
        if(thietBi.getImgSignature()!=null) {
            Bitmap bitmapToImage = BitmapFactory.decodeByteArray(thietBi.getImgSignature(), 0, thietBi.getImgSignature().length);
            myViewHolder.info_signature.setImageBitmap(bitmapToImage);
        }

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null)
                    listener.onClick(myViewHolder.getAdapterPosition());
            }
        });

    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
