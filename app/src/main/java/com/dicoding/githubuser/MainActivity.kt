package com.dicoding.githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.addAll(listUsers)
        showRecycleList()
    }

    private val listUsers: ArrayList<User>
        get(){
            val dataName = resources.getStringArray(R.array.data_name)
            val dataUsername = resources.getStringArray(R.array.data_username)
            val dataLocation = resources.getStringArray(R.array.data_location)
            val dataRepository = resources.getStringArray(R.array.data_repository)
            val dataCompany = resources.getStringArray(R.array.data_company)
            val dataFollowers = resources.getStringArray(R.array.data_followers)
            val dataFollowing = resources.getStringArray(R.array.data_following)
            val dataProfile = resources.obtainTypedArray(R.array.data_avatar)
            val listUser = ArrayList<User>()
            for (i in dataName.indices){
                val user = User(dataName[i], dataUsername[i], dataLocation[i], dataRepository[i], dataCompany[i],
                    dataFollowers[i], dataFollowing[i], dataProfile.getResourceId(i, -1))
                listUser.add(user)
            }
            dataProfile.recycle()
            return listUser
        }

    private fun showRecycleList(){
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
        val listUserAdapter = ListUserAdapter(list)
        binding.rvUsers.adapter = listUserAdapter
        
        listUserAdapter.setOnItemClickCallback(object : ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                showSelectedUser(data)
            }
        })
    }

    private fun showSelectedUser(user: User){
        val detailUserIntent = Intent(this@MainActivity, UserDetailActivity::class.java)
        detailUserIntent.putExtra(UserDetailActivity.EXTRA_USER, user)
        startActivity(detailUserIntent)
    }
}