/**
 * author : Sudeera Madushan
 * date : 11/1/2023
 * project : Front-End
 */

export class Booking {
    constructor(packageId, packageCategoryId,areaList,hotelList,vehicle,noOfChildren,totalHeadCount,dates,packageValue,customerId) {
        this._name = name;
        this._packageCategoryId = packageCategoryId;
        this._areaList = areaList;
        this._hotelList = hotelList;
        this._vehicle =vehicle;
        this._noOfChildren =noOfChildren;
        this._totalHeadCount =totalHeadCount;
        this._dates=dates;
        this._packageValue=packageValue;
        this._customerId=customerId;
    }

    get customerId() {
        return this._customerId;
    }

    set customerId(value) {
        this._customerId = value;
    }

    get packageValue() {
        return this._packageValue;
    }

    set packageValue(value) {
        this._packageValue = value;
    }

    get dates() {
        return this._dates;
    }

    set dates(value) {
        this._dates = value;
    }

    get noOfChildren() {
        return this._noOfChildren;
    }

    set noOfChildren(value) {
        this._noOfChildren = value;
    }

    get totalHeadCount() {
        return this._totalHeadCount;
    }

    set totalHeadCount(value) {
        this._totalHeadCount = value;
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