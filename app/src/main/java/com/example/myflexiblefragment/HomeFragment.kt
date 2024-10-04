package com.example.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit

//Fragment di activty
class HomeFragment : Fragment(), View.OnClickListener {

    /*
    layout interface didefinisikan dan ditransformasikan dari layout
    berupa file xml ke dalam objek view dengan menggunakan metode inflate().
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    /*
     Di sini kita bisa gunakan untuk inisialisasi view, dan juga mengatur action-nya (set listener).
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnCategory:Button = view.findViewById(R.id.btn_category) // perlu menggunakan view untuk menggunakan
        btnCategory.setOnClickListener(this)                           // findViewById
    }

    override fun onClick(v: View) {
        if (v?.id == R.id.btn_category){
            val categoryManager = CategoryFragment()
            val fragmentManager = parentFragmentManager // menggunakan parentFragmentManager untuk mendapatkan instance FragmentManager dari Activity di dalam Fragment
            // menggunakan KTX
            fragmentManager.commit {
                addToBackStack(null)
                replace(R.id.frame_container, categoryManager, CategoryFragment::class.java.simpleName)
            }
//            fragmentManager.beginTransaction().apply {
//                replace(R.id.frame_container, categoryManager, CategoryFragment::class.java.simpleName) // replace() ketika ingin menempelkan sebuah fragment baru.
//                addToBackStack(null)
//                commit()
//            }

        }
    }
}