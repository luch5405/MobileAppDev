package com.example.lab7

data class PuppyCare(var name: String = "", var url: String = "", var LngLat: String = "") {
    fun suggestPuppyCare(position: Int){
        setPuppyCareName(position)
        setPuppyCareURL(position)
        setPuppyCareLngLat(position)
    }

    private fun setPuppyCareLngLat(position: Int) {
        LngLat = when(position){
            0 -> "40.024335,-105.247107"
            1 -> "40.018874,-105.251724"
            2 -> "40.024335,-105.247107"
            3 -> "40.033988,-105.183380"
            else -> "0,0"
        }
    }

    private fun setPuppyCareName(position: Int){
        name = when(position){
            0 -> "Camp Bow Wow Boulder, they are best for small dogs"
            1 -> "PetSmart, this is the best option for almost all medium and small dogs"
            2 -> "Home Buddies, most of the dogs they take in are in this weight class"
            3 -> "Cotton Wood Kennels, since big dogs can hurt smaller dogs, Cotton Wood Kennels only takes large dogs"
            else -> "Rover, it is hard to find day care huge dogs, look for an independent care taker"
        }
    }

    private fun setPuppyCareURL(position: Int){
        url = when(position){
            0 -> "https://www.campbowwow.com/Boulder/Services-Pricing/Dog-Day-Care/?gclid=Cj0KCQiA15yNBhDTARIsAGnwe0XR07i5Im7HHAzdD1kQQSgegMpLvS9dfjBpM05kaibWo4ZwJXFMxNQaAnEpEALw_wcB"
            1 -> "https://services.petsmart.com/petshotel/1915?utm_source=google&utm_medium=organic&utm_campaign=google-my-business"
            2 -> "http://www.myhomebuddies.com/boulder"
            3 -> "https://www.cottonwoodkennels.com/"
            else -> "https://www.rover.com/"
        }
    }

}