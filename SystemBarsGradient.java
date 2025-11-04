public static void setSystemBarsGradient(Activity activity) {
        ColorDrawable background = new ColorDrawable(ContextCompat.getColor(activity, R.color.status_bar));
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            // API 35 以下
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, android.R.color.transparent));
            activity.getWindow().setBackgroundDrawable(background);
        } else {
            View contentView = activity.findViewById(android.R.id.content);

            // 動態偵測插邊（狀態列 + 導覽列）
            ViewCompat.setOnApplyWindowInsetsListener(contentView, (v, insets) -> {
                Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(bars.left, bars.top, bars.right, bars.bottom);
                return WindowInsetsCompat.CONSUMED;
            });

            // 設定背景（保持你的漸層樣式）
            contentView.setBackground(background);
        }
    }
