package com.dicoding.githubuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.githubuser.databinding.ActivityUserDetailBinding
import com.dicoding.githubuser.databinding.LayoutCompanyLocationBinding
import com.dicoding.githubuser.databinding.LayoutFollowersBinding
import com.dicoding.githubuser.databinding.LayoutRepositoryBinding

class UserDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailBinding
    private lateinit var followerBinding: LayoutFollowersBinding
    private lateinit var repositoryBinding: LayoutRepositoryBinding
    private lateinit var companyLocationBinding: LayoutCompanyLocationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title = getString(R.string.detail_user)

        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        followerBinding = LayoutFollowersBinding.bind(binding.root)
        repositoryBinding = LayoutRepositoryBinding.bind(binding.root)
        companyLocationBinding = LayoutCompanyLocationBinding.bind(binding.root)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(EXTRA_USER) as User
        Glide.with(this)
                .load(user.avatar)
                .circleCrop()
                .into(binding.imgDetailAvatar)
        binding.tvDetailName.text = user.name
        binding.tvDetailUsername.text = user.username
        followerBinding.tvDetailFollowers.text = getString(R.string.followers, user.followers)
        followerBinding.tvDetailFollowing.text = getString(R.string.following, user.following)
        repositoryBinding.tvDetailRepository.text = getString(R.string.repository, user.repository)
        companyLocationBinding.tvDetailCompanyValue.text = user.company
        companyLocationBinding.tvDetailLocationValue.text = user.location
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    companion object{
        const val EXTRA_USER = "extra_user"
    }
}