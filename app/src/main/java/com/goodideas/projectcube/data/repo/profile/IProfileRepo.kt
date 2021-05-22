package com.goodideas.projectcube.data.repo.profile

import com.goodideas.projectcube.data.dto.profile.ProfileRes
import com.goodideas.projectcube.data.dto.profile.UpdateProfileReq
import com.goodideas.projectcube.data.dto.profile.UpdateProfileRes
import retrofit2.Response

interface IProfileRepo {
    suspend fun getUserProfile(userId: Int): Response<ProfileRes>

    suspend fun updateProfile(req: UpdateProfileReq): Response<UpdateProfileRes>
}