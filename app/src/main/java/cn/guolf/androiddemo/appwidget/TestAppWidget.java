package cn.guolf.androiddemo.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import cn.guolf.androiddemo.R;
import cn.guolf.androiddemo.ui.MainActivity;
import cn.guolf.androiddemo.util.LogUtil;

/**
 * Implementation of App Widget functionality.
 */
public class TestAppWidget extends AppWidgetProvider {

    private String update_action ="com.guolf.androiddemo.update";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.test_app_widget);
        views.setTextViewText(R.id.appwidget_text,widgetText);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        LogUtil.i("½ÓÊÕ¹ã²¥£º"+intent.getAction());
        if(intent.getAction()==update_action){
            LogUtil.i("update by Broadcast");
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.test_app_widget);
            views.setTextViewText(R.id.appwidget_text, "update by Broadcast");
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        for(int i : appWidgetIds){
            LogUtil.i("onDeleted:"+i);
        }
    }
}

