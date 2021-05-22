package com.goodideas.projectcube.data.repo.profile

import com.goodideas.projectcube.data.dto.profile.ProfileRes
import com.goodideas.projectcube.data.dto.profile.UpdateProfileReq
import com.goodideas.projectcube.data.dto.profile.UpdateProfileRes
import com.goodideas.projectcube.data.network.ApiService
import retrofit2.Response

class ProfileRepo(private val source: ApiService) : IProfileRepo {
    override suspend fun getUserProfile(userId: Int): Response<ProfileRes> {
        return source.retrofit.getProfile(userId)
    }

    override suspend fun updateProfile(req: UpdateProfileReq): Response<UpdateProfileRes> {
        return source.retrofit.updateProfile(req)
    }
}