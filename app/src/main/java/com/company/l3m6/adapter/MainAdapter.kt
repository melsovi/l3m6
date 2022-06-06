package com.company.l3m6.adapter

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.company.l3m6.R
import com.company.l3m6.databinding.ItemGalleryBinding

class MainAdapter(private val listener: Listener) : RecyclerView.Adapter<MainAdapter.AdapterHolder>() {
    private val imageList = arrayListOf<Uri>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_gallery, parent, false)
        return AdapterHolder(view)
    }

    override fun onBindViewHolder(holder: AdapterHolder, position: Int) {
        holder.bind(imageList[position], listener)
    }

    override fun getItemCount() = imageList.size


    class AdapterHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemGalleryBinding.bind(item)

        fun bind(mainImage: Uri, listener: Listener) = with(binding) {
            imageRecycler.setImageURI(mainImage) //Тут устанавливаем изображение
            imageRecyclerBorder.visibility = INVISIBLE //Чтобы тени не было сразу
            itemView.setOnClickListener { //Слушатель на изображенеи
                if (!imageRecyclerBorder.isVisible) { // Проверка то что тени выключены
                    listener.onClick(mainImage) // Тут идет нажатие на изображение
                    imageRecyclerBorder.visibility = VISIBLE // Установка невидимости
                } else  {
                    listener.deleteClick(mainImage)
                    imageRecyclerBorder.visibility = INVISIBLE // Установка видимости
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addImage(image: Uri) {
        this.imageList.addAll(listOf(image))
        notifyDataSetChanged()
    }

    interface Listener {
        fun onClick(mainImage: Uri)
        fun deleteClick(mainImage: Uri)
    }
}