package com.example.myflexiblefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit

// Flexible Fragment di Satu Activity
class CategoryFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnDetailCategory: Button = view.findViewById(R.id.btn_detail_category)
        btnDetailCategory.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
       if (v?.id == R.id.btn_detail_category){
           val detailCategoryFragment = DetailCategoryFragment()

            val bundle = Bundle() // bundle untuk mengirimkan data antar fragment.
            bundle.putString(DetailCategoryFragment.EXTRA_NAME, "Lifestyle")
            detailCategoryFragment.arguments = bundle

           val description = "Kategori ini akan berisi produk-produk lifestyle"
           detailCategoryFragment.description = description // menggunakan setter untuk mengirim data ke DetailCategoryFragment

           val fragmentManager = parentFragmentManager
           // menggunakan KTX
           fragmentManager.commit {
               addToBackStack(null)
               replace(R.id.frame_container, detailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
           }
//           fragmentManager?.beginTransaction()?.apply {
//               replace(R.id.frame_container, detailCategoryFragment, DetailCategoryFragment::class.java.simpleName)
//               addToBackStack(null)
//               commit()
//           }
       }
    }

}