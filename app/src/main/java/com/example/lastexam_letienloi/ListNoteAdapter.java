package com.example.lastexam_letienloi;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastexam_letienloi.model.ListNote;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class ListNoteAdapter extends RecyclerView.Adapter<ListNoteAdapter.ListNoteViewHolder>{
    List<ListNote> list;
    SQLiteHelpterProject sqLiteHelpterProject;
    Context context;
    int notificationId=1;
    final String CHANNEL_ID = "101";
    public ListNoteAdapter() {
        list=new ArrayList<>();
    }
    public void getAll(List<ListNote> list){
        this.list=list;
    }

    @NonNull
    @Override
    public ListNoteAdapter.ListNoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.list_note,parent,false);
        context = v.getContext();
        sqLiteHelpterProject=new SQLiteHelpterProject(context);
        createNotificationChannel(context);
        return new ListNoteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNoteAdapter.ListNoteViewHolder holder, int position) {
        ListNote listNote=list.get(position);
        System.out.println(list.size());
        holder.txtTitle.setText(listNote.getTitle());
        holder.txtdes.setText(listNote.getDes());
        holder.txtdatetime.setText(listNote.getDateTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(),ListNoteActivity.class);
                intent.putExtra("id",String.valueOf(listNote.getId()));
                intent.putExtra("title",listNote.getTitle());
                intent.putExtra("des",listNote.getDes());
                intent.putExtra("datetime",listNote.getDateTime());
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.btNoty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dateTime = listNote.getDateTime();
                String [] date_time = dateTime.split("-");
                String date=date_time[0];
                String time=date_time[1];
                String[] tmp = time.split(":");
                int tmpHour = Integer.parseInt(tmp[0]);
                int tmpMinute = Integer.parseInt(tmp[1]);

                String[] dateF = date.split("/");
                int dayF = Integer.parseInt(dateF[0]);
                int monthF = Integer.parseInt(dateF[1]);
                int yearF = Integer.parseInt(dateF[2]);
                System.out.println(dayF+ " " + monthF + " " + yearF);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true) {
                            Calendar c = Calendar.getInstance();
                            int hour, minute, day, month, year;
                            hour = c.get(Calendar.HOUR_OF_DAY);
                            minute = c.get(Calendar.MINUTE);
                            day = c.get(Calendar.DAY_OF_MONTH);
                            month = c.get(Calendar.MONTH) + 1;
                            year = c.get(Calendar.YEAR);
                            if (hour == tmpHour && minute == tmpMinute && day == dayF && month == monthF && year == yearF) {
//                                Button btt = (Button) ((Activity) view.getContext()).findViewById(R.id.bt);
//                                btt.setEnabled(true);
                                NotificationCompat.Builder builder =
                                        new NotificationCompat.Builder(context,CHANNEL_ID)
                                                .setSmallIcon(R.drawable.ic_launcher_background)
                                                .setContentTitle("Thông báo")
                                                .setContentText(listNote.getTitle())
                                                .setColor(Color.RED)
                                                .setPriority(NotificationCompat.PRIORITY_HIGH)
                                                .setAutoCancel(true);
                                NotificationManagerCompat notificationManagerCompat =
                                        NotificationManagerCompat.from(context);
                                notificationId = notificationId + 1;
                                notificationManagerCompat.notify(notificationId, builder.build());
                                break;
                            }
                        }
                    }
                }).start();
//                holder.btNoty.setEnabled(false);
            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteHelpterProject.deleteListnote(listNote.getId());
                list.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    public class ListNoteViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle,txtusername,txtdes,txtdatetime;
        Button btNoty, btDelete;
        public ListNoteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtdes=itemView.findViewById(R.id.txtddes);
            txtdatetime=itemView.findViewById(R.id.txtdatetime);
            btNoty=itemView.findViewById(R.id.bt);
            btDelete=itemView.findViewById(R.id.delete);
        }
    }

    private void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Thông báo",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            channel.setDescription("Đây là kênh thông báo");
            NotificationManager manager =
                    context.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
