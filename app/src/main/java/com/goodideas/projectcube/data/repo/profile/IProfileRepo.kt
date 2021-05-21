package com.goodideas.projectcube.data.repo.profile

import com.goodideas.projectcube.data.dto.profile.ProfileRes
import retrofit2.Response

interface IProfileRepo {
    suspend fun getUserProfile(userId: Int): Response<ProfileRes>
}