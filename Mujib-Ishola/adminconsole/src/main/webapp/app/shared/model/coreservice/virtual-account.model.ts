import { ProfileType } from 'app/shared/model/enumerations/profile-type.model';
import { AccountStatus } from 'app/shared/model/enumerations/account-status.model';

export interface IVirtualAccount {
  id?: number;
  customerId?: string;
  extCustomerId?: string;
  currency?: string;
  accountNumber?: string;
  availableBalance?: number;
  holdBalance?: number;
  minimumBalance?: number;
  accountType?: ProfileType;
  status?: AccountStatus;
  accountHolderEmail?: string;
  accountHolderId?: number;
  transactionRequestId?: number;
}

export class VirtualAccount implements IVirtualAccount {
  constructor(
    public id?: number,
    public customerId?: string,
    public extCustomerId?: string,
    public currency?: string,
    public accountNumber?: string,
    public availableBalance?: number,
    public holdBalance?: number,
    public minimumBalance?: number,
    public accountType?: ProfileType,
    public status?: AccountStatus,
    public accountHolderEmail?: string,
    public accountHolderId?: number,
    public transactionRequestId?: number
  ) {}
}
