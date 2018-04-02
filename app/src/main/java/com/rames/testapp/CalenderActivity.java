package com.rames.testapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by yangju1 on 17/5/4.
 * yangju1@staff.weibo.com
 */
public class CalenderActivity extends Activity {
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    //Android2.2版本以后的URL，之前的就不写了
    private static String calanderURL = "content://com.android.calendar/calendars";
    private static String calanderEventURL = "content://com.android.calendar/events";
    private static String calanderRemiderURL = "content://com.android.calendar/reminders";

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, CalenderActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calender_layout);

        insertDummyContactWrapper();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void insertDummyContactWrapper() {
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_CALENDAR);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] {Manifest.permission.WRITE_CALENDAR}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.readUserButton) {  //读取系统日历账户，如果为0的话先添加
            Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);

            System.out.println("Count: " + userCursor.getCount());
            Toast.makeText(this, "Count: " + userCursor.getCount(), Toast.LENGTH_LONG).show();

            for (userCursor.moveToFirst(); !userCursor.isAfterLast(); userCursor.moveToNext()) {
                System.out.println("name: " + userCursor.getString(userCursor.getColumnIndex("ACCOUNT_NAME")));


                String userName1 = userCursor.getString(userCursor.getColumnIndex("name"));
                String userName0 = userCursor.getString(userCursor.getColumnIndex("ACCOUNT_NAME"));
                Toast.makeText(this, "NAME: " + userName1 + " -- ACCOUNT_NAME: " + userName0, Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.inputaccount) {        //添加日历账户
            initCalendars();

        } else if (v.getId() == R.id.delEventButton) {  //删除事件

            int rownum = getContentResolver().delete(Uri.parse(calanderURL), "_id!=-1", null);  //注意：会全部删除所有账户，新添加的账户一般从id=1开始，
            //可以令_id=你添加账户的id，以此删除你添加的账户
            Toast.makeText(this, "删除了: " + rownum, Toast.LENGTH_LONG).show();

        } else if (v.getId() == R.id.readEventButton) {  //读取事件
            Cursor eventCursor = getContentResolver().query(Uri.parse(calanderEventURL), null, null, null, null);
            if (eventCursor.getCount() > 0) {
                eventCursor.moveToLast();             //注意：这里与添加事件时的账户相对应，都是向最后一个账户添加
                String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                Toast.makeText(this, eventTitle, Toast.LENGTH_LONG).show();
            }
        } else if (v.getId() == R.id.writeEventButton) {
            // 获取要出入的gmail账户的id
            String calId = "";
            Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);
            if (userCursor.getCount() > 0) {
                userCursor.moveToLast();  //注意：是向最后一个账户添加，开发者可以根据需要改变添加事件 的账户
                calId = userCursor.getString(userCursor.getColumnIndex("_id"));
            } else {
                Toast.makeText(this, "没有账户，请先添加账户", 0).show();
                return;
            }

            ContentValues event = new ContentValues();
            event.put("title", "与马云在天桥下会晤");
            event.put("description", "Frankie受空姐邀请,今天晚上10点以后将在Sheraton动作交流.lol~");
            // 插入账户
            event.put("calendar_id", calId);
            System.out.println("calId: " + calId);
            event.put("eventLocation", "地球-华夏");

            Calendar mCalendar = Calendar.getInstance();
            mCalendar.set(Calendar.HOUR_OF_DAY, 20);
            mCalendar.set(Calendar.MINUTE, 00);
            long start = mCalendar.getTime().getTime();
            mCalendar.set(Calendar.HOUR_OF_DAY, 23);
            long end = mCalendar.getTime().getTime();

            event.put("dtstart", start);
            event.put("dtend", end);
            event.put("hasAlarm", 1);

            event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");  //这个是时区，必须有，
            //添加事件
            Uri newEvent = getContentResolver().insert(Uri.parse(calanderEventURL), event);
            //事件提醒的设定
            long id = Long.parseLong(newEvent.getLastPathSegment());
            ContentValues values = new ContentValues();
            values.put("event_id", id);
            // 提前10分钟有提醒
            values.put("minutes", 1);
            getContentResolver().insert(Uri.parse(calanderRemiderURL), values);

            Toast.makeText(this, "插入事件成功!!!", Toast.LENGTH_LONG).show();
        }
    }


    //添加账户
    private void initCalendars() {

        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, "yy");

        value.put(CalendarContract.Calendars.ACCOUNT_NAME, "mygmailaddress@gmail.com");
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, "com.android.exchange");
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, "mytt");
        value.put(CalendarContract.Calendars.VISIBLE, 1);
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, -9206951);
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, "mygmailaddress@gmail.com");
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = CalendarContract.Calendars.CONTENT_URI;
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, "mygmailaddress@gmail.com")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, "com.android.exchange")
                .build();

        getContentResolver().insert(calendarUri, value);
    }
}
