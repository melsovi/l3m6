package com.company.l3m6

import android.net.Uri
import android.view.LayoutInflater
import androidx.recyclerview.widget.GridLayoutManager
import com.company.l3m6.adapter.SelectedImageAdapter
import com.company.l3m6.base.BaseActivity
import com.company.l3m6.databinding.ActivitySelectedImageBinding

class SelectedImageActivity : BaseActivity<ActivitySelectedImageBinding>() {

    private val adapter = SelectedImageAdapter()

    override fun inflateVB(inflater: LayoutInflater): ActivitySelectedImageBinding {
        return ActivitySelectedImageBinding.inflate(inflater)
    }

    override fun initListener() {
        val uri: ArrayList<Uri>? = intent.getParcelableArrayListExtra(MainActivity.KEY_IMG)
        if (uri != null) {
            adapter.addImage(uri)
        }
    }

    override fun initView() {
        binding.selectedRecycler.layoutManager = GridLayoutManager(this@SelectedImageActivity, 3)
        binding.selectedRecycler.adapter = adapter
    }
}