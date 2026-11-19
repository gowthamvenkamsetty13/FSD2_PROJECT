$ErrorActionPreference = 'Stop'
$docPath = Join-Path (Get-Location) 'fsd2_project_report.docx'
$pdfPath = Join-Path (Get-Location) 'fsd2_project_report.pdf'
$word = New-Object -ComObject Word.Application
$word.Visible = $false
$word.DisplayAlerts = 0
$doc = $word.Documents.Open($docPath, $false, $false)
try {
    $doc.Repaginate()
    # Heading paragraph positions in the report, mapped to their actual page numbers.
    $headingItems = @{
        '1'=127; '1.1'=128; '1.2'=130; '1.3'=132; '1.4'=139; '1.5'=141;
        '2'=151; '2.1'=152; '2.2'=154; '2.3'=156; '2.4'=158; '2.5'=160;
        '3'=162; '3.1'=163; '3.2'=172; '3.3'=177; '3.4'=184; '3.5'=192;
        '4'=195; '4.1'=196; '4.2'=198; '4.3'=200; '4.4'=202; '4.5'=204; '4.6'=206;
        '5'=209; '6'=235; '7'=239; '8'=252
    }
    $headingPages = @{}
    foreach ($number in $headingItems.Keys) {
        $headingPages[$number] = $doc.Paragraphs.Item($headingItems[$number]).Range.Information(3)
    }
    for ($i = 73; $i -le 103; $i++) {
        $tocPara = $doc.Paragraphs.Item($i)
        $tocText = $tocPara.Range.Text.Trim()
        if ($tocText -match '^((?:\d+\.)*\d+)\.?\s') {
            $number = $Matches[1]
            if ($headingPages.ContainsKey($number)) {
                $cleanText = [regex]::Replace($tocText, '\d+\s*$', [string]$headingPages[$number])
                $tocPara.Range.Text = $cleanText + "`r"
            }
        }
    }
    foreach ($pair in @(@{Index=102; PageItem=263}, @{Index=103; PageItem=270})) {
        $tocPara = $doc.Paragraphs.Item($pair.Index)
        $tocText = $tocPara.Range.Text.Trim()
        $actualPage = $doc.Paragraphs.Item($pair.PageItem).Range.Information(3)
        $cleanText = [regex]::Replace($tocText, '\d+\s*$', [string]$actualPage)
        $tocPara.Range.Text = $cleanText + "`r"
    }
    [void]$doc.Fields.Update()
    $doc.Repaginate()
    $doc.Save()
    $doc.ExportAsFixedFormat($pdfPath, 17)
}
finally {
    $doc.Close(0)
    $word.Quit()
    [void][System.Runtime.InteropServices.Marshal]::ReleaseComObject($doc)
    [void][System.Runtime.InteropServices.Marshal]::ReleaseComObject($word)
}
Get-Item -LiteralPath $docPath, $pdfPath | Select-Object Name,Length
