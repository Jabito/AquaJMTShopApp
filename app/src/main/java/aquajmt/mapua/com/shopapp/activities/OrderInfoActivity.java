package aquajmt.mapua.com.shopapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import aquajmt.mapua.com.shopapp.R;
import aquajmt.mapua.com.shopapp.api.models.OrderInfo;
import aquajmt.mapua.com.shopapp.utils.SharedPref;
import butterknife.BindView;
import butterknife.OnClick;

public class OrderInfoActivity extends AppCompatActivity {

    private OrderInfo currentOrder;

    @BindView(R.id.tv_customer_name)
    TextView tvCustomerName;

    @BindView(R.id.tv_customer_address)
    TextView tvCustomerAddress;

    @BindView(R.id.tv_customer_contact)
    TextView tvCustomerContact;

    @BindView(R.id.tv_water_type)
    TextView tvWaterType;

    @BindView(R.id.tv_round_ordered)
    TextView tvROundOrdered;

    @BindView(R.id.tv_slim_ordered)
    TextView tvSlimOrdered;

    @BindView(R.id.tv_total_cost)
    TextView tvTotalCost;

    @BindView(R.id.tv_order_details)
    TextView tvOrderDetails;

    @BindView(R.id.btn_accept)
    Button btnAccept;

    @BindView(R.id.btn_decline)
    Button btnDecline;

    @BindView(R.id.btn_back)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);

        currentOrder = new OrderInfo();
        currentOrder = SharedPref.currentOrder;

        tvCustomerName.setText(currentOrder.getCustomerName());
        tvCustomerAddress.setText(currentOrder.getCustomerAddress());

        Toast.makeText(getApplicationContext(), currentOrder.getId(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_accept)
    void btnAcceptOnClick(){

    }

    @OnClick(R.id.btn_back)
    void btnBackOnClick(){
        startActivity(new Intent(OrderInfoActivity.this, DashboardActivity.class));
    }
}
