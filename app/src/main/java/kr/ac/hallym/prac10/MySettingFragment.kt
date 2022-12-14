package kr.ac.hallym.prac10

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat


class MySettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.setting,rootKey)

//        val b2Preference: EditTextPreference? = findPreference("b2")
//        b2Preference?.isVisible = false

//        b2Preference?.summary = "code summary"
//        b2Preference?.title = "code title"

//        b2Preference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> {
//            val text = it.text
//            if(TextUtils.isEmpty(text)){
//                "설정되지 않았습니다."
//            }
//            else{
//                "설정된 값은 $text 입니다."
//            }
//        }
    }

}