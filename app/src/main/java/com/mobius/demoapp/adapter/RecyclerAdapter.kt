package com.mobius.demoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mobius.demoapp.R
import com.mobius.demoapp.response.APIResponse

class RecyclerAdapter  (private val list: List<APIResponse>)
    : RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: APIResponse = list[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int = list.size

}

class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.result_item, parent, false)) {
    private var mCouponCode: TextView? = null
    private var mRibbonMessage: TextView? = null
    private var mCouponDiscount : TextView?=null
    private var mValidTill: TextView?=null
    private var mDeposit: TextView?=null
    private var mBonus: TextView?=null
    private var mBonusExpiry: TextView?=null
    private var mSlab: TextView?=null
    private var mBonusAmt: TextView?=null
    private var mInstant: TextView?=null


    init {
        mCouponCode = itemView.findViewById(R.id.txt_coupon_code)
        mRibbonMessage = itemView.findViewById(R.id.txt_rbn_msg)
        mCouponDiscount= itemView.findViewById(R.id.txt_discount)
        mValidTill= itemView.findViewById(R.id.txt_valid)
        mDeposit= itemView.findViewById(R.id.txt_deposit)
        mBonus= itemView.findViewById(R.id.txt_bonus)
        mBonusExpiry= itemView.findViewById(R.id.txt_expiry)
        mSlab=itemView.findViewById(R.id.txt_slab)
        mBonusAmt=itemView.findViewById(R.id.txt_bonus_amt)
        mInstant =itemView.findViewById(R.id.txt_instant)
    }

    fun bind(movie: APIResponse) {
        mCouponCode?.text = movie.code
        mRibbonMessage?.text = movie.ribbon_msg
        val totalValue = movie.slabs[0]. wagered_percent + movie.slabs[0]. otc_percent
        val totalAmount = movie.slabs[0]. wagered_max + movie.slabs[0]. otc_max

        mCouponDiscount?.text="Get "+totalValue.toString()+" % "+" upto $ "+totalAmount
        mValidTill?.text="Valid Till"+movie.valid_until
        mDeposit?.text ="Min Deposit" + movie.slabs[0].min.toString() +"Applied"
        mBonus?.text ="For Every" + movie.wager_to_release_ratio_numerator.toString() +" bet "+movie.wager_to_release_ratio_denominator +" will be released from the bonus amount"

        mBonusExpiry?.text ="Bonus expiry "+ movie.wager_bonus_expiry.toString()+" days from the issue "
        mSlab?.text =movie.slabs[0].min.toString() +" & "+movie.slabs[0].max.toString()
        mBonusAmt?.text =movie.slabs[0].wagered_percent.toString() +" % "+" Max(Rs "+movie.slabs[0].wagered_max+" )"
        mInstant?.text =movie.slabs[0].otc_percent.toString() +" % "+" Max(Rs "+movie.slabs[0].otc_max+" )"
    }

}