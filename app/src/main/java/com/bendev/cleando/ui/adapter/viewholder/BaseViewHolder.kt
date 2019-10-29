package com.bendev.cleando.ui.adapter.viewholder

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Benoit on 22/06/2017.
 */
open class BaseViewHolder(parent: android.view.ViewGroup, layoutRes: Int) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutRes, parent, false))