package testXMLConsume;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.imie.DTO.SecondServiceBean;
import org.imie.DTO.SecondaryServiceRoot;

public class ConsumerMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			URL url = new URL(
					"http://localhost:8080/RestTestApplication/serviceInitiale");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/xml");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			System.out.println("*** flux rest **********");
			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			Source xmlSource = new StreamSource(conn.getInputStream());

			Source xsltSource = new StreamSource(new File(
					"src/beanTransform.xslt"));

			File temp = File.createTempFile("tempfile", ".tmp");
			StreamResult out = new StreamResult(temp);

			TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer trans = transFact.newTransformer(xsltSource);
			trans.transform(xmlSource, out);

			String strLine;
			BufferedReader br2 = new BufferedReader(new InputStreamReader(
					new DataInputStream(new FileInputStream((temp)))));
			System.out.println("*** xml transformé **********");
			while ((strLine = br2.readLine()) != null) {
				// Print the content on the console
				System.out.println(strLine);
			}

			JAXBContext jaxbContext = JAXBContext
					.newInstance(SecondaryServiceRoot.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SecondaryServiceRoot secondaryServiceRoot = (SecondaryServiceRoot) jaxbUnmarshaller
					.unmarshal(temp);

			System.out.println("*** objet après unmarchal **********");
			for (SecondServiceBean secondServiceBean : secondaryServiceRoot
					.getSecondServiceBeans()) {
				System.out.println(secondServiceBean.toString());

			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
