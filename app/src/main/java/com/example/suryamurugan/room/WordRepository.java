package com.example.suryamurugan.room;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class WordRepository {

    private WordDao mwordDao;
    private LiveData<List<Word>> mAllWords;


  WordRepository(Application application){
      WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
      mwordDao = db.wordDao();

      mAllWords = mwordDao.getAllWords();

  }


  LiveData<List<Word>> getmAllWords(){
      return mAllWords;
  }

    public void insert (Word word) {
        new insertAsyncTask(mwordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
