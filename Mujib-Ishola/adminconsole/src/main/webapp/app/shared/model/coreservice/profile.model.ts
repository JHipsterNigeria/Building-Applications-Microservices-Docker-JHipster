export interface IProfile {
  id?: number;
  userId?: number;
  login?: string;
  phone?: string;
  email?: string;
  firstName?: string;
  middleName?: string;
  lastName?: string;
  optActivationKey?: string;
  activationKey?: string;
  alias?: string;
  emailNotifications?: boolean;
  smsNotifications?: boolean;
  photoContentType?: string;
  photo?: any;
  lang?: string;
}

export class Profile implements IProfile {
  constructor(
    public id?: number,
    public userId?: number,
    public login?: string,
    public phone?: string,
    public email?: string,
    public firstName?: string,
    public middleName?: string,
    public lastName?: string,
    public optActivationKey?: string,
    public activationKey?: string,
    public alias?: string,
    public emailNotifications?: boolean,
    public smsNotifications?: boolean,
    public photoContentType?: string,
    public photo?: any,
    public lang?: string
  ) {
    this.emailNotifications = this.emailNotifications || false;
    this.smsNotifications = this.smsNotifications || false;
  }
}
