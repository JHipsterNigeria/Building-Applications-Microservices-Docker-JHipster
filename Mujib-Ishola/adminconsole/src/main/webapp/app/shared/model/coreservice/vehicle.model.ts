import { VehicleType } from 'app/shared/model/enumerations/vehicle-type.model';

export interface IVehicle {
  id?: number;
  vin?: string;
  type?: VehicleType;
  ownerId?: number;
}

export class Vehicle implements IVehicle {
  constructor(public id?: number, public vin?: string, public type?: VehicleType, public ownerId?: number) {}
}
