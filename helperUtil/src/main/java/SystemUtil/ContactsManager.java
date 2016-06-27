package SystemUtil;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nam Tran on 05-Jan-16.
 */
public class ContactsManager {

    Context mContext;

    public ContactsManager(Context context) {
        this.mContext = context;
    }

    public int getSizeContactsHavePhone() {
        try {
            ContentResolver cr = mContext.getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            int count = 0;
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    boolean checkExits = false;
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        Cursor pCur = cr
                                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                                + " = ?", new String[]{id},
                                        null);
                        while (pCur.moveToNext()) {
                            String phonetype = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            String MainNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            if (phonetype != null && phonetype.equalsIgnoreCase("1")) {
                                PhoneNumber number = new PhoneNumber();
                                number.setNumber(MainNumber);
                                number.setType("home");
                                checkExits = true;
                            } else if (phonetype != null && phonetype.equalsIgnoreCase("2")) {
                                PhoneNumber number = new PhoneNumber();
                                number.setNumber(MainNumber);
                                number.setType("mobile");
                                checkExits = true;
                            } else {
                                PhoneNumber number = new PhoneNumber();
                                number.setNumber(MainNumber);
                                number.setType("work");
                                checkExits = true;
                            }
                        }
                        if (checkExits) {
                            count++;
                            System.out.println("CSDT");
                        }
                        pCur.close();
                    }
                }
            }
            return count;
        } catch (NullPointerException ex) {
            Log.d("ERROR", ex.toString());
            return 0;
        }
    }

    public List<PhoneContact> getContactsHavePhone() {
        ArrayList<PhoneContact> contacts = new ArrayList<PhoneContact>();
        try {
            ContentResolver cr = mContext.getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
                    // System.out.println("SIZE " + cur.getCount());
            int count = 0;
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    PhoneContact contact = new PhoneContact();
                    // GET CONTACT ID/NAME
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    contact.setId(id);
                    contact.setName(name);
                    boolean check = false;
                    // GET NUMBERS
                    if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        Cursor pCur = cr
                                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                                + " = ?", new String[]{id},
                                        null);
                        while (pCur.moveToNext()) {
                            String phonetype = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            String MainNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            if (phonetype != null && phonetype.equalsIgnoreCase("1")) {
                                PhoneNumber number = new PhoneNumber();
                                number.setNumber(MainNumber);
                                number.setType("home");
                    // add contact's phone number
                                contact.getNumbers().add(number);
                            } else if (phonetype != null && phonetype.equalsIgnoreCase("2")) {
                                PhoneNumber number = new PhoneNumber();
                                number.setNumber(MainNumber);
                                number.setType("mobile");
                    // add contact's phone number
                                contact.getNumbers().add(number);
                            } else {
                                PhoneNumber number = new PhoneNumber();
                                number.setNumber(MainNumber);
                                number.setType("work");
                    // add contact's phone number
                                contact.getNumbers().add(number);
                            }
                            if (contact.getNumbers().size() > 0)
                                check = true;
                        }
                        pCur.close();
                    }
                    if (check) {
                        System.out.println("CO SDT");
                    // GET ADDRESSES
                        Cursor addrCur = cr.query(
                                ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
                                null,
                                ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ?",
                                new String[]{id}, null);
                        if (addrCur.getCount() > 0) {
                            while (addrCur.moveToNext()) {
                                String poBox = addrCur
                                        .getString(addrCur
                                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
                                if (poBox == null) {
                                    poBox = " ";
                                }
                                String street = addrCur
                                        .getString(addrCur
                                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                                if (street == null) {
                                    street = " ";
                                }
                                String neb = addrCur
                                        .getString(addrCur
                                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD));
                                if (neb == null) {
                                    neb = " ";
                                }
                                String city = addrCur
                                        .getString(addrCur
                                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                                if (city == null) {
                                    city = " ";
                                }
                                String state = addrCur
                                        .getString(addrCur
                                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                                if (state == null) {
                                    state = " ";
                                }
                                String postalCode = addrCur
                                        .getString(addrCur
                                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                                if (postalCode == null) {
                                    postalCode = " ";
                                }
                                String country = addrCur
                                        .getString(addrCur
                                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                                if (country == null) {
                                    country = " ";
                                }
                                String type = addrCur
                                        .getString(addrCur
                                                .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));
                                if (type == null) {
                                    type = " ";
                                }
                    // create address info
                                Address address = new Address();
                                address.setPoBox(poBox);
                                address.setType(type);
                                address.setCity(city);
                                address.setCountry(country);
                                address.setNeb(neb);
                                address.setPostalCode(postalCode);
                                address.setState(state);
                                address.setStreet(street);
                    // add contact's addresses
                                contact.getAddresses().add(address);
                            }
                        }
                        addrCur.close();
                    // GET NOTES
                        String noteWhere = ContactsContract.Data.CONTACT_ID
                                + " = ? AND " + ContactsContract.Data.MIMETYPE
                                + " = ?";
                        String[] noteWhereParams = new String[]{
                                id,
                                ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE};
                        Cursor noteCur = cr.query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null);
                        if (noteCur.moveToFirst()) {
                            String note = noteCur.getString(noteCur.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));
                            if (note == null) {
                                note = " ";
                            }
                    // add contact's notes
                            contact.getNotes().add(note);
                        }
                        noteCur.close();
                    // GET ORGANIZATION NAME
                        String orgWhere = ContactsContract.Data.CONTACT_ID
                                + " = ? AND " + ContactsContract.Data.MIMETYPE
                                + " = ?";
                        String[] orgWhereParams = new String[]{id, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE};
                        Cursor orgCur = cr.query(ContactsContract.Data.CONTENT_URI, null, orgWhere, orgWhereParams, null);
                        if (orgCur.moveToFirst()) {
                            String orgName = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY));
                            if (orgName == null) {
                                orgName = " ";
                            }
                    // set contact organization
                            contact.setOrganization(orgName);
                        }
                        orgCur.close();
                    // GET EMAILS
                        Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                        while (emailCur.moveToNext()) {
                            String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                            String emailType = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
                            if (email == null) {
                                email = "unknown";
                            }
                            if (emailType.equalsIgnoreCase("1")) {
                            } else {
                            }
                    // set contact's email
                            contact.getEmails().add(email);
                        }
                        emailCur.close();
                    // Log.d("DBG", "Name: " + name);
                        contacts.add(contact);
                        count++;
                    } else {
                        System.out.println("KHONG SDT");
                    }
                }
            }
        } catch (NullPointerException ex) {
            Log.d("ERROR", ex.toString());
        }
        return contacts;
    }

    /**
     * Get contacts list
     * Note:
     * This function requires permission in manifest file:
     * <uses-permission android:name="android.permission.READ_CONTACTS" />
     *
     * @return
     */
    public List<PhoneContact> getAllContacts() {
        ArrayList<PhoneContact> contacts = new ArrayList<>();
        try {
            ContentResolver cr = mContext.getContentResolver();
            Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
            if (cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    PhoneContact contact = new PhoneContact();
                    // GET CONTACT ID/NAME
                    String id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID));
                    String name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    contact.setId(id);
                    contact.setName(name);
                    // GET NUMBERS
                    if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                        Cursor pCur = cr
                                .query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                                        null,
                                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                                + " = ?", new String[]{id},
                                        null);
                        while (pCur.moveToNext()) {
                            String phonetype = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            String MainNumber = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            if (phonetype != null && phonetype.equalsIgnoreCase("1")) {
                                PhoneNumber number = new PhoneNumber();
                                number.setNumber(MainNumber);
                                number.setType("home");
                                // add contact's phone number
                                contact.getNumbers().add(number);
                            } else if (phonetype != null && phonetype.equalsIgnoreCase("2")) {
                                PhoneNumber number = new PhoneNumber();
                                number.setNumber(MainNumber);
                                number.setType("mobile");
                                // add contact's phone number
                                contact.getNumbers().add(number);
                            } else {
                                PhoneNumber number = new PhoneNumber();
                                number.setNumber(MainNumber);
                                number.setType("work");
                                // add contact's phone number
                                contact.getNumbers().add(number);
                            }
                        }
                        pCur.close();
                    }
                    // GET ADDRESSES
                    Cursor addrCur = cr.query(
                            ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.StructuredPostal.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    if (addrCur.getCount() > 0) {
                        while (addrCur.moveToNext()) {
                            String poBox = addrCur
                                    .getString(addrCur
                                            .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX));
                            if (poBox == null) {
                                poBox = " ";
                            }
                            String street = addrCur
                                    .getString(addrCur
                                            .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET));
                            if (street == null) {
                                street = " ";
                            }
                            String neb = addrCur
                                    .getString(addrCur
                                            .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD));
                            if (neb == null) {
                                neb = " ";
                            }
                            String city = addrCur
                                    .getString(addrCur
                                            .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY));
                            if (city == null) {
                                city = " ";
                            }
                            String state = addrCur
                                    .getString(addrCur
                                            .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION));
                            if (state == null) {
                                state = " ";
                            }
                            String postalCode = addrCur
                                    .getString(addrCur
                                            .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE));
                            if (postalCode == null) {
                                postalCode = " ";
                            }
                            String country = addrCur
                                    .getString(addrCur
                                            .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY));
                            if (country == null) {
                                country = " ";
                            }
                            String type = addrCur
                                    .getString(addrCur
                                            .getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE));
                            if (type == null) {
                                type = " ";
                            }
                            // create address info
                            Address address = new Address();
                            address.setPoBox(poBox);
                            address.setType(type);
                            address.setCity(city);
                            address.setCountry(country);
                            address.setNeb(neb);
                            address.setPostalCode(postalCode);
                            address.setState(state);
                            address.setStreet(street);
                            // add contact's addresses
                            contact.getAddresses().add(address);
                        }
                    }
                    addrCur.close();
                    // GET NOTES
                    String noteWhere = ContactsContract.Data.CONTACT_ID
                            + " = ? AND " + ContactsContract.Data.MIMETYPE
                            + " = ?";
                    String[] noteWhereParams = new String[]{
                            id,
                            ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE};
                    Cursor noteCur = cr.query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null);
                    if (noteCur.moveToFirst()) {
                        String note = noteCur.getString(noteCur.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE));
                        if (note == null) {
                            note = " ";
                        }
                        // add contact's notes
                        contact.getNotes().add(note);
                    }
                    noteCur.close();
                    // GET ORGANIZATION NAME
                    String orgWhere = ContactsContract.Data.CONTACT_ID
                            + " = ? AND " + ContactsContract.Data.MIMETYPE
                            + " = ?";
                    String[] orgWhereParams = new String[]{id, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE};
                    Cursor orgCur = cr.query(ContactsContract.Data.CONTENT_URI, null, orgWhere, orgWhereParams, null);
                    if (orgCur.moveToFirst()) {
                        String orgName = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY));
                        if (orgName == null) {
                            orgName = " ";
                        }
                        // set contact organization
                        contact.setOrganization(orgName);
                    }
                    orgCur.close();
                    // GET EMAILS
                    Cursor emailCur = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                    while (emailCur.moveToNext()) {
                        String email = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                        String emailType = emailCur.getString(emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE));
                        if (email == null) {
                            email = "unknown";
                        }
                        if (emailType.equalsIgnoreCase("1")) {
                        } else {
                        }
                        // set contact's email
                        contact.getEmails().add(email);
                    }
                    emailCur.close();
                    // Log.d("DBG", "Name: " + name);
                    contacts.add(contact);
                }
            }
        } catch (NullPointerException ex) {
            Log.d("ERROR", ex.toString());
        }
        return contacts;
    }

    /**
     * Add contact
     * Note:
     * This function requires permission in manifest file:
     * <uses-permission android:name="android.permission.WRITE_CONTACTS" />
     *
     * @param name
     * @param mobile
     * @param home
     * @param email
     */
    public void add(String name, String mobile, String home, String email) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<>();
        int rawContactID = ops.size();
        // Adding insert operation to operations list
        // to insert a new raw contact in the table ContactsContract.RawContacts
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        // Adding insert operation to operations list
        // to insert display name in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name)
                .build());
        // Adding insert operation to operations list
        // to insert Mobile Number in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, mobile)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build());
        // Adding insert operation to operations list
        // to insert Home Phone Number in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, home)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                .build());
        // Adding insert operation to operations list
        // to insert Home Email in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, email)
                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME)
                .build());
        // Adding insert operation to operations list
        // to insert Work Email in the table ContactsContract.Data
        // ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
        // .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
        // .withValue(ContactsContract.Data.MIMETYPE, Email.CONTENT_ITEM_TYPE)
        // .withValue(Email.ADDRESS, workEmail)
        // .withValue(Email.TYPE, Email.TYPE_WORK)
        // .build());
        try {
            // Executing all the insert operations as a single database transaction
            mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add contact
     * Note:
     * This function requires permission in manifest file:
     * <uses-permission android:name="android.permission.WRITE_CONTACTS" />
     *
     * @param contact
     */
    public void addContacts(PhoneContact contact) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContactID = ops.size();
        // Adding insert operation to operations list
        // to insert a new raw contact in the table ContactsContract.RawContacts
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());
        // Adding insert operation to operations list
        // to insert display name in the table ContactsContract.Data
        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, contact.getName())
                .build());
        // Adding insert operation to operations list
        // to insert Mobile Number in the table ContactsContract.Data
        List<PhoneNumber> numbers = contact.getNumbers();
        for (ContactsManager.PhoneNumber number : numbers) {
            // home = 1
            if ("home".equalsIgnoreCase(number.getType())) {
                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number.getNumber())
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                        .build());
            } else if ("mobile".equalsIgnoreCase(number.getType())) {
                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number.getNumber())
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                        .build());
            } else if ("WORK".equalsIgnoreCase(number.getType())) {
                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number.getNumber())
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                        .build());
            } else {
                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number.getNumber())
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                        .build());
            }
        }
        // Adding insert operation to operations list
        // to insert Home Email in the table ContactsContract.Data
        List<String> emails = contact.getEmails();
        for (String email : emails) {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Email.ADDRESS, email)
                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_HOME)
                    .build());
        }
        try {
            // Executing all the insert operations as a single database transaction
            mContext.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete contact
     *
     * @param phone
     * @param name
     * @return
     */
    public boolean delete(String phone, String name) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phone));
        Cursor cur = mContext.getContentResolver().query(contactUri, null, null, null, null);
        try {
            if (cur.moveToFirst()) {
                do {
                    if (cur.getString(cur.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)).equalsIgnoreCase(name)) {
                        String lookupKey = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));
                        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                        mContext.getContentResolver().delete(uri, null, null);
                        return true;
                    }
                } while (cur.moveToNext());
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return false;
    }

    /**
     * Phone contact class
     */
    public static class PhoneContact implements Serializable {
        private String id;
        private String name;
        private String organization;
        private List<PhoneNumber> numbers;
        private List<String> emails;
        private List<Address> addresses;
        private List<String> notes;

        public PhoneContact() {
            this.numbers = new ArrayList<>();
            this.emails = new ArrayList<>();
            this.addresses = new ArrayList<>();
            this.notes = new ArrayList<>();
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public List<PhoneNumber> getNumbers() {
            return numbers;
        }

        public void setNumbers(List<PhoneNumber> numbers) {
            this.numbers = numbers;
        }

        public List<String> getEmails() {
            return emails;
        }

        public void setEmails(List<String> emails) {
            this.emails = emails;
        }

        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }

        public List<String> getNotes() {
            return notes;
        }

        public void setNotes(List<String> notes) {
            this.notes = notes;
        }
    }

    /**
     * Phone number class
     */
    public static class PhoneNumber {
        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        private String number;
        private String type;
    }

    /**
     * Contact address class
     */
    public static class Address {
        private String poBox;
        private String street;
        private String neb;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private String type;

        public String getPoBox() {
            return poBox;
        }

        public void setPoBox(String poBox) {
            this.poBox = poBox;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getNeb() {
            return neb;
        }

        public void setNeb(String neb) {
            this.neb = neb;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getPostalCode() {
            return postalCode;
        }

        public void setPostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
