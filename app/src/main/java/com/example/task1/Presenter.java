package com.example.task1;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.adapters.places_adapter;
import com.michaldrabik.tapbarmenulib.TapBarMenu;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class Presenter {
    TapBarMenu tapBarMenu ;
    ImageView Home,Profile ,Search , Notification ;
    AppCompatActivity context;
    static Presenter presenter;
    static  Presenter getInstance(){
     if (presenter==null)
         presenter= new Presenter();
     return presenter;
    }

    private Presenter() {
    }

    void presenter_init(AppCompatActivity compat){
    context=compat;
    intialize_fields();
    intialize_actions();
}
void intialize_actions(){
    Home.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Home.setImageResource(R.drawable.home_bottom_icon);
            Profile.setImageResource(R.drawable.profile_grey_bottom_icon);
            Search.setImageResource(R.drawable.search_grey_bottom_icon);
            Notification.setImageResource(R.drawable.notification_grey_bottom_icon);

        }
    });
    Profile.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Profile.setImageResource(R.drawable.profile_bottom_icon);
            Home.setImageResource(R.drawable.home_grey_bottom_icon);
            Search.setImageResource(R.drawable.search_grey_bottom_icon);
            Notification.setImageResource(R.drawable.notification_grey_bottom_icon);

        }
    });
    Search.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Search.setImageResource(R.drawable.search_bottom_icon);
            Profile.setImageResource(R.drawable.profile_grey_bottom_icon);
            Home.setImageResource(R.drawable.home_grey_bottom_icon);
            Notification.setImageResource(R.drawable.notification_grey_bottom_icon);

        }
    });
    Notification.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Home.setImageResource(R.drawable.home_grey_bottom_icon);
            Profile.setImageResource(R.drawable.profile_grey_bottom_icon);
            Search.setImageResource(R.drawable.search_grey_bottom_icon);
            Notification.setImageResource(R.drawable.notification_bottom_icon);

        }
    });

}

    void intialize_fields(){
        tapBarMenu= context.findViewById(R.id.tapBarMenu);
        Home= context.findViewById(R.id.item1);
        Profile= context.findViewById(R.id.item4);
        Search= context.findViewById(R.id.item2);
        Notification= context.findViewById(R.id.item3);

        ImageView icon = new ImageView(context); // Create an icon
        icon.setImageResource(R.drawable.bellman_bottom_icon);
        FloatingActionButton actionButton = new FloatingActionButton.Builder(context)
                .setContentView(icon).build();
        actionButton.setPosition(5,new FloatingActionButton.LayoutParams(150,150));

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(context);

        ImageView itemIcon = new ImageView(context);
        itemIcon.setImageResource(R.drawable.hotspot_icon);
        ImageView itemIcon2 = new ImageView(context);
        itemIcon2.setImageResource(R.drawable.events_icon);
        ImageView itemIcon3 = new ImageView(context);
        itemIcon3.setImageResource(R.drawable.attarctions_icon);
        ImageView itemIcon4= new ImageView(context);
        itemIcon4.setImageResource(R.drawable.small_grey_location_pin);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon).build();
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();
        FloatingActionMenu.Builder actionMenu = new FloatingActionMenu.Builder(context);
        actionMenu.addSubActionView(button1).addSubActionView(button2).addSubActionView(button3).addSubActionView(button4).attachTo(actionButton).build();

        Toolbar myToolbar = context.findViewById(R.id.my_toolbar);

        context.setSupportActionBar(myToolbar);
    }

}
