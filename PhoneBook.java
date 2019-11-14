// Phone Book Exercise
// Java Implementation
// Lewis Brown - 13/11/2019

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class PhoneBook {

    private static int MAX_SZ = 10000000;
    // For gettig input
    private FastScanner in = new FastScanner();
    // Keep list of all existing (i.e. not deleted yet) contacts.
    private Contact[] contacts = new Contact[MAX_SZ];

    public static void main(String[] args) {
    // processes the queries input by the user
        new PhoneBook().processQueries();
    }

    private Query readQuery() {
    //  Reads a single query from input.
    //  There are three possible formats:
    //    1.  find - name
    //    2.  find - number
    //    3.  add - name - number
    //  The function returns a Query type object of the appropriate format.   
        String type = in.next();     
        int number = in.nextInt();
        if (type.equals("add")) {
	    // if necessary get the third string - this is only necessary for add type queries
            String name = in.next();
            return new Query(type, name, number);
        } else {
            return new Query(type, number);
        }
    }

    private void writeResponse(String response) {
    // Writes a response to output
        System.out.println(response);
    }


    private void processQuery(Query query) {
    // Writes a String response to output based on the current queries
        if (query.type.equals("add")) {
	    contacts[query.number] = new Contact(query.name, query.number);
        } else if (query.type.equals("del")) {
            contacts[query.number] = null;
        } else {
	    Contact c = contacts[query.number];
            String response = "not found";
	    if(c!=null){
		response = c.name;
	    } 
            writeResponse(response);
        }
    }

    public void processQueries() {
    // Processes a series of Queries provided by the user
        int queryCount = in.nextInt();
	for(int i=0; i<queryCount; i++){
	    processQuery(readQuery()); 
	}
    }

    static class Contact {
        String name;
        int number;

        public Contact(String name, int number) {
            this.name = name;
            this.number = number;
        }
    }

    static class Query {
        String type;
        String name;
        int number;

        public Query(String type, String name, int number) {
            this.type = type;
            this.name = name;
            this.number = number;
        }

        public Query(String type, int number) {
            this.type = type;
            this.number = number;
        }
    }

    class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

