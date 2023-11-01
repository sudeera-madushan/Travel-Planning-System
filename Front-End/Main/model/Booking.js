/**
 * author : Sudeera Madushan
 * date : 11/1/2023
 * project : Front-End
 */

export class Booking {
    constructor(packageId, packageCategoryId,areaList,hotelList) {
        this._name = name;
        this._packageCategoryId = packageCategoryId;
        this._areaList = areaList;
        this._hotelList = hotelList;
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
}