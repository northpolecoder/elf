package northpole.nick.elf;

import android.content.Context;
import android.os.AsyncTask;

public class CheckNameTask extends AsyncTask<String, String, Boolean> {

    private final Context context;
    private final ListChecker listChecker = new ListChecker();

    public CheckNameTask(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            final String name = params[0];
            publishProgress(context.getString(R.string.checking_list));
            Thread.sleep(2000);
            publishProgress(context.getString(R.string.checking_twice));
            Thread.sleep(2000);
            return listChecker.naughtyOrNice(name);
        } catch (Exception e) {
            return false;
        }
    }
}
