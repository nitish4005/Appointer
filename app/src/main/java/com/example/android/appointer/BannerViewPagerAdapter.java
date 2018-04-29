package com.example.android.appointer;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Prasad on 20-Apr-18.
 */

public class BannerViewPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    int[] banners;

    public BannerViewPagerAdapter(Context context, int[] banners) {
        this.context = context;
        this.banners = banners;
    }
    @Override
    public int getCount() {
        return banners.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.banner_layout, null);
        ImageView bannerImage = (ImageView) view.findViewById(R.id.bannerImage);
//        if (!TextUtils.isEmpty(banners.get(position).getImageUrl())) {
//            Picasso.with(context).load(banners.get(position).getImageUrl())
//                    .placeholder(R.drawable.transparent_logo)
//                    .into(bannerImage);
//        }
//
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(banners.get(position).getType().equals(BannerTypeEnum.URL.getValue())) {
//                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(banners.get(position).getUrl()));
//                    context.startActivity(browserIntent);
//                } else if(banners.get(position).getType().equals(BannerTypeEnum.CONTENT.getValue())){
//                    ((MainActivity) context).goToChannelAndPlayContent(banners.get(position).getContentId().toString());
//                }
//
//            }
//        });
        bannerImage.setImageResource(banners[position]);
        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);

    }
}


