/**
 * author : Sudeera Madushan
 * date : 11/1/2023
 * project : Front-End
 */

export class Person {
    constructor(packageId, age) {
        this._name = name; // Note the use of an underscore to indicate a private property
        this._age = age;
    }

    // Getter for the name property
    get name() {
        return this._name;
    }

    // Setter for the name property
    set name(newName) {
        if (typeof newName === 'string') {
            this._name = newName;
        } else {
            console.error('Name must be a string');
        }
    }
}