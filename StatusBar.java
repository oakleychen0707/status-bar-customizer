public static void setStatusBar(Activity activity) {
    ColorDrawable background = new ColorDrawable(ContextCompat.getColor(activity, R.color.color_primary));

    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        // API 34 and below
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, android.R.color.transparent));
        activity.getWindow().setBackgroundDrawable(background);
    } else {
        // Set additional top padding for API 35 and above
        View decorView = activity.getWindow().getDecorView();
        decorView.setBackground(background);
        int statusBarHeight = getStatusBarHeight(activity);

        decorView.setPadding(
            decorView.getPaddingLeft(),
            statusBarHeight,
            decorView.getPaddingRight(),
            decorView.getPaddingBottom()
        );
    }
}

// Get Status Bar Height
private static int getStatusBarHeight(Context context) {
    int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
        return context.getResources().getDimensionPixelSize(resourceId);
    }
    return 0;
}
