package com.airasia.swf.module.engineersList.response

class EngineersListResponse{

    var engineers: ArrayList<Engineers>? = null


    inner class Engineers{
        var id: Int? = null

        var name: String? = null
    }
}