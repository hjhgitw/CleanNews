package clean.news.ui

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import clean.news.CleanNewsApplication
import clean.news.R
import clean.news.flow.ComponentService
import clean.news.flow.SceneDispatcher
import flow.Flow
import flow.KeyDispatcher

abstract class BaseActivity : AppCompatActivity() {
	abstract fun getDefaultKey(): Any

	override fun attachBaseContext(newBase: Context) {
		val applicationComponent = CleanNewsApplication.get(newBase).component()
		val context = Flow.configure(newBase, this)
				.addServicesFactory(ComponentService(applicationComponent))
				.dispatcher(KeyDispatcher.configure(this, SceneDispatcher(this)).build())
				.defaultKey(getDefaultKey())
				.install()

		super.attachBaseContext(context)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_base)
	}

	override fun onBackPressed() {
		if (!Flow.get(this).goBack()) {
			super.onBackPressed()
		}
	}
}