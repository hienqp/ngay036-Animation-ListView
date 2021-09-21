package com.hienqp.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class FruitAdapter extends BaseAdapter {
    // Context, là Activity chỉ định sẽ hiển thị
    private Context context;
    // UI XML của từng item hiển thị trên ListView mà ta đã xây dựng sẵn, tương tự như android.R.layout.simple_layout_item_1 của hệ thống
    private int layoutItem;
    // List danh sách chứa các đối tượng Fruit
    private List<Fruit> fruitList;

    public FruitAdapter(Context context, int layoutItem, List<Fruit> fruitList) {
        this.context = context;
        this.layoutItem = layoutItem;
        this.fruitList = fruitList;
    }

    // trả về số dòng cho ListView
    // lấy số lượng phần tử trong fruitList trả về số dòng cho ListView
    @Override
    public int getCount() {
        return fruitList.size();
    }

    // trả về đối tượng item trong ListView
    // phương thức này chưa cần sử dụng, để mặc định
    @Override
    public Object getItem(int position) {
        return null;
    }

    // trả về ID của đối tượng item trong ListView
    // phương thức này chưa cần sử dụng, để mặc định
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // khai báo 1 private class ViewHolder
    private class ViewHolder {
        ImageView imageViewPhoto;
        TextView textViewName, textViewDescription;
    }

    // phương thức trả về mỗi View (item) cho ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /** Sử dụng ViewHolder*/
        ViewHolder viewHolder;

        // kiểm tra convertView (item) đã được load lên ListView chưa
        // nếu chưa load (==null) ta mới tiến hành tạo ánh xạ
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutItem, null);

            viewHolder = new ViewHolder();
            // ánh xạ
            // bây giờ giá trị của item sẽ được lưu giữ trong viewHolder
            viewHolder.imageViewPhoto = (ImageView) convertView.findViewById(R.id.imageview_fruit_photo);
            viewHolder.textViewName = (TextView) convertView.findViewById(R.id.textview_fruit_name);
            viewHolder.textViewDescription = (TextView) convertView.findViewById(R.id.textview_fruit_description);
            // sau khi có viewHolder setTag() nó cho convertView (item), lúc này item đã có giá trị
            convertView.setTag(viewHolder);
        } else {
            // viewHolder đã được setTag() cho convertView, chỉ việc lấy viewHolder ra sử dụng
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // gán giá trị
        Fruit fruit = fruitList.get(position);

        // gán giá trị cho viewHolder giữ
        viewHolder.imageViewPhoto.setImageResource(fruit.getFruitPhoto());
        viewHolder.textViewName.setText(fruit.getFruitName());
        viewHolder.textViewDescription.setText(fruit.getFruitDescription());


        /** không sử dụng ViewHolder*/
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        convertView = inflater.inflate(layoutItem, null);
//        // ánh xạ
//        ImageView fruitPhoto = (ImageView) convertView.findViewById(R.id.imageview_fruit_photo);
//        TextView fruitName = (TextView) convertView.findViewById(R.id.textview_fruit_name);
//        TextView fruitDescription = (TextView) convertView.findViewById(R.id.textview_fruit_description);

//        // gán giá trị
//        Fruit fruit = fruitList.get(position);

          // gán giá trị đối tượng Fruit
//        fruitPhoto.setImageResource(fruit.getFruitPhoto());
//        fruitName.setText(fruit.getFruitName());
//        fruitDescription.setText(fruit.getFruitDescription());

        /** Sử dụng Animation Scale cho mỗi View xuất hiện trên ListView */
        Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim_scale);
        convertView.startAnimation(animation);

        return convertView;
    }
}
