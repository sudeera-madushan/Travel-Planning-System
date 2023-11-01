/**
 * author : Sudeera Madushan
 * date : 11/1/2023
 * project : Front-End
 */

export class Booking {
    constructor(packageId, packageCategoryId,areaList,hotelList,vehicle) {
        this._name = name;
        this._packageCategoryId = packageCategoryId;
        this._areaList = areaList;
        this._hotelList = hotelList;
        this._vehicle =vehicle;
    }

    get name() {
        return this._name;
    }
    set name(newName) {
        this._name = newName;
    }

    set packageCategoryId(value) {
        this._packageCategoryId = value;
    }
    get packageCategoryId() {
        return this._packageCategoryId;
    }

    get areaList() {
        return this._areaList;
    }

    set areaList(value) {
        this._areaList = value;
    }

    get hotelList() {
        return this._hotelList;
    }

    set hotelList(value) {
        this._hotelList = value;
    }


    get vehicle() {
        return this._vehicle;
    }

    set vehicle(value) {
        this._vehicle = value;
    }
}