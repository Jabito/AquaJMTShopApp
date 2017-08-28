package aquajmt.mapua.com.shopapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.api.models.OrderInfo;
import aquajmt.mapua.com.shopapp.utils.WaterType;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Bryan on 7/29/2017.
 */

public class OrderArrayAdapter extends ArrayAdapter<OrderInfo> {

    private final boolean listForShop;
    private Context context;

    public OrderArrayAdapter(Context context, ArrayList<OrderInfo> orderPartialList) {
        super(context, 0, orderPartialList);
        this.context = context;
        this.listForShop = true;
    }

    public OrderArrayAdapter(Context context, ArrayList<OrderInfo> orderPartialList,
                             boolean listForShop) {
        super(context, 0, orderPartialList);
        this.context = context;
        this.listForShop = listForShop;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_order_partial, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        OrderInfo orderPartial = getItem(position);
        if (orderPartial != null) {
            if (listForShop) {
                viewHolder.setCustomerName(orderPartial.getCustomerName());
            } else {
                viewHolder.setShopName(orderPartial.getShopName());
            }
            // viewHolder.setStatus(orderPartial.getStatus());
            viewHolder.setTotalPrice(orderPartial.getTotalCost());
            viewHolder.setWaterType(orderPartial.getWaterType());
            viewHolder.setRoundCount(orderPartial.getRoundOrdered());
            viewHolder.setSlimCount(orderPartial.getSlimOrdered());

            Integer ratingGiven = orderPartial.getRatingGiven();
            if (ratingGiven == null) {
                viewHolder.hideRating();
            } else {
                viewHolder.showRating(ratingGiven);
            }

            try {
                viewHolder.setOrderDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(orderPartial.getCreatedOn()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return convertView;
    }

    static class ViewHolder implements View.OnClickListener {
        @BindView(R.id.img_status)
        ImageView imgStatus;

        @BindView(R.id.txt_entity_name)
        TextView txtEntityName;

        @BindView(R.id.txt_order_date)
        TextView txtOrderDate;

        @BindView(R.id.txt_total_price)
        TextView txtTotalPrice;

        @BindView(R.id.txt_water_type)
        TextView txtWaterType;

        @BindView(R.id.txt_round_count)
        TextView txtRoundCount;

        @BindView(R.id.txt_slim_count)
        TextView txtSlimCount;

        @BindView(R.id.img_rating)
        ImageView imgRating;

        @BindView(R.id.txt_rating)
        TextView txtRating;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        void setStatus(String status) {
            throw new UnsupportedOperationException();
        }

        void setStatus(int status) {
            throw new UnsupportedOperationException();
        }

        void setShopName(String shopName) {
            txtEntityName.setText(shopName);
        }

        void setCustomerName(String customerName) {
            txtEntityName.setText(customerName);
        }

        private static final SimpleDateFormat sdf =
                new SimpleDateFormat("EEE HH:mm dd MMM yyyy", Locale.getDefault());
        void setOrderDate(Date orderDate) {
            txtOrderDate.setText(sdf.format(orderDate));
        }

        void setTotalPrice(double totalPrice) {
            txtTotalPrice.setText(String.format(Locale.getDefault(), "%.2f", totalPrice));
        }

        void setWaterType(String waterTypeStr) {
            String waterType;
            switch (waterTypeStr) {
                case WaterType.ALKALINE_STR:
                    waterType = "Alkaline";
                    break;
                case WaterType.PURIFIED_STR:
                    waterType = "Purified";
                    break;
                case WaterType.DISTILLED_STR:
                    waterType = "Distilled";
                    break;
                case WaterType.MINERAL_STR:
                    waterType = "Mineral";
                    break;
                default:
                    throw new IllegalArgumentException("Unknown water type.");
            }
            txtWaterType.setText(waterType);
        }

        void setWaterType(int waterType) {
            String waterTypeStr;
            switch (waterType) {
                case WaterType.ALKALINE:
                    waterTypeStr = "Alkaline";
                    break;
                case WaterType.PURIFIED:
                    waterTypeStr = "Purified";
                    break;
                case WaterType.DISTILLED:
                    waterTypeStr = "Distilled";
                    break;
                case WaterType.MINERAL:
                    waterTypeStr = "Mineral";
                    break;
                default:
                    throw new IllegalArgumentException("Unknown water type.");
            }
            txtWaterType.setText(waterTypeStr);
        }

        void setSlimCount(int count) {
            txtSlimCount.setText(String.valueOf(count));
        }

        void setRoundCount(int count) {
            txtRoundCount.setText(String.valueOf(count));
        }

        void hideRating() {
            imgRating.setVisibility(View.GONE);
            txtRating.setVisibility(View.GONE);
        }

        void showRating(int rating) {
            imgRating.setVisibility(View.VISIBLE);
            txtRating.setText(String.valueOf(rating));
        }

        @Override
        public void onClick(View view) {
            System.out.print("Clicked" + view.toString());
        }
    }

    public interface ArrayAdapterListener{
        void onSelectedOrder(OrderInfo order);
    }
}
